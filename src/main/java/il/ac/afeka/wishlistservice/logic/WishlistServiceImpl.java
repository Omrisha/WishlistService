package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.*;
import il.ac.afeka.wishlistservice.data.ProductEntity;
import il.ac.afeka.wishlistservice.data.UserEntity;
import il.ac.afeka.wishlistservice.data.WishlistDao;
import il.ac.afeka.wishlistservice.data.WishlistEntity;
import il.ac.afeka.wishlistservice.enums.FilterByEnum;
import il.ac.afeka.wishlistservice.enums.SortByEnum;
import il.ac.afeka.wishlistservice.enums.SortOrderEnum;
import il.ac.afeka.wishlistservice.errors.BadRequestException;
import il.ac.afeka.wishlistservice.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {
    private RestTemplate restTemplate;
    private String userServiceUrl;
    private String productsServiceUrl;
    private String reviewsServiceUrl;
    private WishlistDao wishlistDao;

    @Value("${usersService}")
    public void setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    @Value("${productsService}")
    public void setProductsServiceUrl(String productsServiceUrl) {
        this.productsServiceUrl = productsServiceUrl;
    }

    @Value("${reviewsService}")
    public void setReviewsServiceUrl(String reviewsServiceUrl) {
        this.reviewsServiceUrl = reviewsServiceUrl;
    }

    @Autowired
    public WishlistServiceImpl(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public WishlistBoundary create(NewWishlishtBoundary wishlist) {
        System.err.println("From Service: " + wishlist);
        if (wishlist.getUser() == null) {
            throw new RuntimeException("User is not defined");
        }
        if (wishlist.getUser().getEmail() == null) {
            throw new RuntimeException("User email is not defined");
        }

        UserBoundary user = getUserByEmail(wishlist.getUser().getEmail());
        if (user == null) {
            throw new BadRequestException("User with " + wishlist.getUser().getEmail() + " is not found");
        }
        WishlistEntity existEntity = this.wishlistDao.findByName(wishlist.getName());
        if (existEntity != null)
        {
            throw new BadRequestException("Wishlist with this name is already exists.");
        }
        WishlistEntity entity = new WishlistEntity(user.toEntity(), wishlist.getName(), new ArrayList<>());
        entity.setId(user.getEmail() + WishlistEntity.KEY_DELIMETER + wishlist.getName());
        WishlistEntity rv = this.wishlistDao.save(entity);
        return new WishlistBoundary(rv);
    }

    @Override
    public WishlistBoundary getWishlistById(String email, String wishListName) {
        if (wishListName == null) {
            throw new RuntimeException("User is not defined");
        }
        if (email == null) {
            throw new RuntimeException("User email is not defined");
        }

        UserBoundary user = getUserByEmail(email);
        if (user == null) {
            throw new BadRequestException("User with " + email + " is not found");
        }
        WishlistEntity rv = this.wishlistDao.findById(email + WishlistEntity.KEY_DELIMETER + wishListName).orElse(null);
        if (rv == null)
            throw new NotFoundException("Wishlist with the name " + wishListName + " is not exist for " + email);
        WishlistBoundary boundary = new WishlistBoundary(rv);
        rv.getProducts().forEach(e -> {
            ProductBoundary pb = getProductById(e.getProductId());
            if (pb != null) {
                pb.setRating(e.getRating());
                boundary.getProducts().add(pb);
            }
        });
        return boundary;
    }

    @Override
    public void addProduct(String email, String wishListName, AddProductBoundary productBoundary) {
        if (wishListName == null) {
            throw new RuntimeException("User is not defined");
        }
        if (email == null) {
            throw new RuntimeException("User email is not defined");
        }

        ProductBoundary product = getProductById(productBoundary.getProductId());
        if (product == null) {
            throw new BadRequestException("Product is not exists.");
        }
        WishlistEntity wishlistToUpdate = this.wishlistDao.findById(email + WishlistEntity.KEY_DELIMETER + wishListName).orElse(null);
        if (wishlistToUpdate == null)
            throw new NotFoundException("Wishlist with the name " + wishListName + " is not exist for " + email);

        ProductEntity entity = new ProductEntity(product.getId());
        entity.setRating(getRatingByProductId(product.getId()).getRating());
        wishlistToUpdate.addProduct(entity);
        this.wishlistDao.save(wishlistToUpdate);
    }

    @Override
    public WishlistBoundary[] getAll(FilterByEnum filterBy, String filterValue, SortByEnum sortBy, SortOrderEnum sortOrder, int size, int page) {
        List<WishlistEntity> wishlists = new ArrayList<>();
        Sort.Direction dir = sortOrder.equals(SortOrderEnum.DESC) ? Sort.Direction.DESC : Sort.Direction.ASC;

        switch (filterBy) {
            case customerEmail:
                wishlists = this.wishlistDao.findAllByUser(new UserEntity(filterValue), PageRequest.of(page, size, dir, sortBy.toString()));
                break;
            case productId:
                wishlists = this.wishlistDao.findAll(PageRequest.of(page, size, dir, sortBy.toString())).getContent();
                wishlists = wishlists.stream().filter(w -> findProductInWishlist(filterValue, w.getProducts())).collect(Collectors.toList());
                break;
            case none:
            default:
                wishlists = this.wishlistDao.findAll(PageRequest.of(page, size, dir, sortBy.toString())).getContent();
                break;
        }

        List<WishlistBoundary> wishlistBoundaries = new ArrayList<>();
        wishlistBoundaries = wishlists.stream().map(WishlistBoundary::new).collect(Collectors.toList());
        List<WishlistEntity> finalWishlists = wishlists;
        wishlistBoundaries.forEach(wb -> {
            List<ProductBoundary> boundaries = new ArrayList<>();
            List<ProductEntity> entities = finalWishlists.stream().filter(e -> e.getId().equals(wb.getUser().getEmail()+WishlistEntity.KEY_DELIMETER+wb.getName())).findFirst().get().getProducts();
            for (ProductEntity pb: entities) {
                ProductBoundary boundary = getProductById(pb.getProductId());
                boundary.setRating(pb.getRating());
                boundaries.add(boundary);
            }
            wb.setProducts(boundaries);
        });

        return wishlistBoundaries.stream().toArray(WishlistBoundary[]::new);
    }

    @Override
    public void deleteAll() {
        this.wishlistDao.deleteAll();
    }

    private boolean findProductInWishlist(String productId, List<ProductEntity> products) {
        for (ProductEntity customer : products) {
            if (customer.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
    private UserBoundary getUserByEmail(String email) {
        try {
            return this.restTemplate.getForObject(
                    this.userServiceUrl + "/{email}",
                    UserBoundary.class,
                    email);
        } catch (Exception ex) {
            return null;
        }
    }
    private ProductBoundary getProductById(String productId) {
        try {
            return this.restTemplate.getForObject(
                    this.productsServiceUrl + "/products/{productId}",
                    ProductBoundary.class,
                    productId);
        } catch (Exception ex) {
            return null;
        }
    }
    private ProductReviewBoundary getRatingByProductId(String productId) {
        try {
            ProductReviewBoundary product = this.restTemplate.getForObject(
                    this.reviewsServiceUrl + "/average_rating/{productId}",
                    ProductReviewBoundary.class,
                    productId);

            if (product == null)
                return new ProductReviewBoundary(productId, -1);
            return product;
        } catch (Exception ex) {
            return new ProductReviewBoundary(productId, -1);
        }
    }
}
