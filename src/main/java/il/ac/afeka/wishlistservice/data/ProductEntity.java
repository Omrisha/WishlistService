package il.ac.afeka.wishlistservice.data;

public class ProductEntity {
    private String productId;

    public ProductEntity(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId='" + productId + '\'' +
                '}';
    }
}
