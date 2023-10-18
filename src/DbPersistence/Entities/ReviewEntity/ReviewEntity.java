package DbPersistence.Entities.ReviewEntity;

public class ReviewEntity {
    private int reviewId;
    private String review;
    private int restaurantId;

    public ReviewEntity(int reviewId, String review, int restaurantId) {
        this.reviewId = reviewId;
        this.review = review;
        this.restaurantId = restaurantId;
    }

    public ReviewEntity() {

    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
