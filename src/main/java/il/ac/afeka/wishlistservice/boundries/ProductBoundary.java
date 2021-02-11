package il.ac.afeka.wishlistservice.boundries;

import il.ac.afeka.wishlistservice.data.ProductEntity;

import java.util.Map;

public class ProductBoundary {
    private String id;
    private String name;
    private double price;
    private String image;
    private Map<String, Object> productDetails;
    private CategoryBoundary category;
    private int rating;

    public ProductBoundary() {
    }

    public ProductBoundary(String productId, String name, int rating) {
        this.id = productId;
        this.name = name;
        this.rating = rating;
    }

    public ProductBoundary(ProductEntity entity) {
        if (entity.getProductId() != null) {
            setId(entity.getProductId());
        }
        setRating(entity.getRating());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "productId='" + id + '\'' +
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
        entity.setProductId(this.getId());
        return entity;
    }
}
