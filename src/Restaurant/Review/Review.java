package Restaurant.Review;

import java.util.Objects;

public class Review {
    private String review;

    public Review(String review) {
        this.review = review;
    }

    public Review() {
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review='" + review + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review1)) return false;
        return review.equals(review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review);
    }
}
