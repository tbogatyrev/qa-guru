package hw18.com.tricentis.demowebshop.products;

public enum Products {
    PHONE_COVER("80");

    final String productId;

    Products(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
