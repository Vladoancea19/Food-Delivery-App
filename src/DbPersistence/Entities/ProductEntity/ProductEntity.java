package DbPersistence.Entities.ProductEntity;

public class ProductEntity {
    private int productId;
    private String productName;
    private String productIngredients;
    private float productsPrice;
    private int productsWeight;
    private int categoryId;

    public ProductEntity(int productId, String productName, String productIngredients, float productsPrice, int productsWeight, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productIngredients = productIngredients;
        this.productsPrice = productsPrice;
        this.productsWeight = productsWeight;
        this.categoryId = categoryId;
    }

    public ProductEntity() {

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductIngredients() {
        return productIngredients;
    }

    public void setProductIngredients(String productIngredients) {
        this.productIngredients = productIngredients;
    }

    public float getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(float productsPrice) {
        this.productsPrice = productsPrice;
    }

    public int getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(int productsWeight) {
        this.productsWeight = productsWeight;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
