package il.ac.afeka.wishlistservice.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishlists")
public class WishlistEntity {
    public static final String KEY_DELIMETER = "#";
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

    @Id
    public String getId() {
        return getUser().getEmail() + KEY_DELIMETER + name;
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

    @Override
    public String toString() {
        return "WishlistEntity{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
