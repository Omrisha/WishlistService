package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.ProductBoundary;
import il.ac.afeka.wishlistservice.boundries.UserBoundary;
import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import il.ac.afeka.wishlistservice.data.ProductEntity;
import il.ac.afeka.wishlistservice.data.UserEntity;
import il.ac.afeka.wishlistservice.data.WishlistDao;
import il.ac.afeka.wishlistservice.data.WishlistEntity;
import il.ac.afeka.wishlistservice.enums.FilterByEnum;
import il.ac.afeka.wishlistservice.enums.SortByEnum;
import il.ac.afeka.wishlistservice.enums.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;

import java.awt.*;
import java.util.ArrayList;

@Service
public class WishlistServiceImpl implements WishlistService {
    private RestTemplate restTemplate;
    private String userServiceUrl;
    private String productsServiceUrl;
    private WishlistDao wishlistDao;

    @Value("${usersService}")
    public void setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    @Value("${productsService}")
    public void setProductsServiceUrl(String productsServiceUrl) {
        this.productsServiceUrl = productsServiceUrl;
    }

    @Autowired
    public WishlistServiceImpl(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public WishlistBoundary create(WishlistBoundary wishlist) {
        System.err.println("From Service: " + wishlist);
        if (wishlist.getUser() == null) {
            throw new RuntimeException("User is not defined");
        }
        if (wishlist.getUser().getEmail() == null) {
            throw new RuntimeException("User email is not defined");
        }

        try {
            UserBoundary user = this.restTemplate.getForObject(
                    this.userServiceUrl + "/{email}",
                    UserBoundary.class,
                    wishlist.getUser().getEmail());
            if (user == null ) {
                throw new NotFoundException("User is not exists.");
            }
            WishlistEntity entity = new WishlistEntity(user.toEntity(), wishlist.getName(), new ArrayList<>());
            WishlistEntity rv = this.wishlistDao.save(entity);
            return new WishlistBoundary(rv);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public WishlistBoundary getWishlistById(String email, String wishListName) {
        return null;
    }

    @Override
    public void addProduct(String email, String wishListName, ProductBoundary productBoundary) {

    }

    @Override
    public WishlistBoundary[] getAll(FilterByEnum filterBy, String filterValue, SortByEnum sortBy, SortOrderEnum sortOrder, int size, int page) {
        return new WishlistBoundary[0];
    }

    @Override
    public void deleteAll() {

    }
}
