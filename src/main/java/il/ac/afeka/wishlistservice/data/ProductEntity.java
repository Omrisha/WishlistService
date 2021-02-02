package il.ac.afeka.wishlistservice.data;

public class ProductEntity {
    private String productId;
    private int rating;

    public ProductEntity() {
    }

    public ProductEntity(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId='" + productId + '\'' +
                ", rating=" + rating +
                '}';
    }
}
