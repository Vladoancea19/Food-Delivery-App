package DbPersistence.Entities.CategoryEntity;

import Restaurant.Category.Product.Product;

import java.util.List;

public class CategoryEntity {
    private int categoryId;
    private String categoryName;
    private List<Product> categoryProducts;
    private int restaurantId;

    public CategoryEntity(int categoryId, String categoryName, List<Product> categoryProducts, int restaurantId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryProducts = categoryProducts;
        this.restaurantId = restaurantId;
    }

    public CategoryEntity() {

    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getCategoryProducts() {
        return categoryProducts;
    }

    public void setCategoryProducts(List<Product> categoryProducts) {
        this.categoryProducts = categoryProducts;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
