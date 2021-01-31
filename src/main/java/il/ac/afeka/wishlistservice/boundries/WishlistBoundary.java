package il.ac.afeka.wishlistservice.boundries;

import il.ac.afeka.wishlistservice.data.ProductEntity;
import il.ac.afeka.wishlistservice.data.WishlistEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WishlistBoundary {
    private UserBoundary user;
    private String name;
    private List<ProductBoundary> products;

    public WishlistBoundary() {
    }

    public WishlistBoundary(UserBoundary user, String name) {
        this.user = user;
        this.name = name;
        products = new ArrayList<>();
    }

    public WishlistBoundary(WishlistEntity entity) {
        setUser(new UserBoundary(entity.getUser()));
        if (entity.getName() != null)
            setName(entity.getName());
        products = new ArrayList<>();
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

    public WishlistBoundary toEntity() {
        WishlistBoundary entity = new WishlistBoundary();
        entity.setName(this.getName());
        entity.setProducts(this.getProducts());
        return entity;
    }
}
