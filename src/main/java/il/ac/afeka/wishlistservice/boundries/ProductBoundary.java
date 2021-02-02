package il.ac.afeka.wishlistservice.boundries;

import il.ac.afeka.wishlistservice.data.ProductEntity;
import il.ac.afeka.wishlistservice.data.UserEntity;

import java.util.Dictionary;
import java.util.Map;

public class ProductBoundary {
    private String productId;
    private String name;
    private double price;
    private String image;
    private Map<String, Object> productDetails;
    private CategoryBoundary category;
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
        setRating(entity.getRating());
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, Object> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Map<String, Object> productDetails) {
        this.productDetails = productDetails;
    }

    public CategoryBoundary getCategory() {
        return category;
    }

    public void setCategory(CategoryBoundary category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductBoundary{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", productDetails=" + productDetails +
                ", category=" + category +
                ", rating=" + rating +
                '}';
    }

    public ProductEntity toEntity() {
        ProductEntity entity = new ProductEntity();
        entity.setProductId(this.getProductId());
        return entity;
    }
}
