package il.ac.afeka.wishlistservice.boundries;

import il.ac.afeka.wishlistservice.data.ProductEntity;
import il.ac.afeka.wishlistservice.data.UserEntity;

public class ProductBoundary {
    private String productId;
    private String name;
    private int rating;

    public ProductBoundary() {
    }

    public ProductBoundary(String productId, String name, int rating) {
        this.productId = productId;
        this.name = name;
        this.rating = rating;
    }

    public ProductBoundary(ProductEntity entity) {
        if (entity.getProductId() != null) {
            setProductId(entity.getProductId());
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductBoundary{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }

    public ProductEntity toEntity() {
        ProductEntity entity = new ProductEntity();
        entity.setProductId(this.getProductId());
        return entity;
    }
}
