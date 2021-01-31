package il.ac.afeka.wishlistservice.boundries;

public class ProductReviewBoundary {
    private String product_id;
    private int rating;

    public ProductReviewBoundary() {
    }

    public ProductReviewBoundary(String product_id, int rating) {
        this.product_id = product_id;
        this.rating = rating;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
