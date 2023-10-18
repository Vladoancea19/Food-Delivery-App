package DbPersistence.Entities.RestaurantEntity;

import Restaurant.Category.Category;
import Restaurant.Review.Review;

import java.util.List;

public class RestaurantEntity {
    private int restaurantId;
    private String restaurantName;
    private List<Review> restaurantReviews;
    private int restaurantScore;
    private List<Category> restaurantCategories;

    public RestaurantEntity(int restaurantId, String restaurantName, List<Review> restaurantReviews, int restaurantScore, List<Category> restaurantCategories) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantReviews = restaurantReviews;
        this.restaurantScore = restaurantScore;
        this.restaurantCategories = restaurantCategories;
    }

    public RestaurantEntity() {

    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public List<Review> getRestaurantReviews() {
        return restaurantReviews;
    }

    public void setRestaurantReviews(List<Review> restaurantReviews) {
        this.restaurantReviews = restaurantReviews;
    }

    public int getRestaurantScore() {
        return restaurantScore;
    }

    public void setRestaurantScore(int restaurantScore) {
        this.restaurantScore = restaurantScore;
    }

    public List<Category> getRestaurantCategories() {
        return restaurantCategories;
    }

    public void setRestaurantCategories(List<Category> restaurantCategories) {
        this.restaurantCategories = restaurantCategories;
    }
}
