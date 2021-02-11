package il.ac.afeka.wishlistservice.data;

import il.ac.afeka.wishlistservice.boundries.ProductBoundary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishlists")
public class WishlistEntity {
    public static final String KEY_DELIMETER = "#";
    @Id
    private String id;
    private UserEntity user;
    private String name;
    private List<ProductEntity> products;

    public WishlistEntity() {
    }

    public WishlistEntity(UserEntity user, String name, List<ProductEntity> products) {
        this.user = user;
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public void addProduct(ProductEntity product) {
        if (products != null) {
            if (!products.contains(product))
                products.add(product);
            else
                throw new RuntimeException("Product " + product.getProductId() + " is already exists.");
        }
    }

    @Override
    public String toString() {
        return "WishlistEntity{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
