package il.ac.afeka.wishlistservice.boundries;

import il.ac.afeka.wishlistservice.data.WishlistEntity;

import java.util.List;
import java.util.stream.Collectors;

public class WishlistBoundary {
    private UserBoundary user;
    private String name;
    private List<ProductBoundary> products;

    public WishlistBoundary() {
    }

    public WishlistBoundary(UserBoundary user, String name, List<ProductBoundary> products) {
        this.user = user;
        this.name = name;
        this.products = products;
    }

    public WishlistBoundary(WishlistEntity entity) {
        setUser(new UserBoundary(entity.getUser()));
        if (entity.getName() != null)
            setName(entity.getName());
        if (products != null && !products.isEmpty())
            setProducts(entity.getProducts().stream().map(ProductBoundary::new).collect(Collectors.toList()));
    }

    public UserBoundary getUser() {
        return user;
    }

    public void setUser(UserBoundary user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(ProductBoundary product) {
        if (products != null) {
            if (!products.contains(product))
                products.add(product);
            else
                throw new RuntimeException("Product " + product.getProductId() + " is already exists.");
        }
    }
    public List<ProductBoundary> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBoundary> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "WishlistBoundary{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
