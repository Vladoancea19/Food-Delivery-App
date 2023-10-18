package Restaurant;

import Restaurant.Category.Category;
import Restaurant.Review.Review;

import java.util.List;
import java.util.Objects;

public class Restaurant {
    private String name;
    private List<Review> reviews;
    private int score;
    private List<Category> categories;

    public Restaurant(String name, List<Review> reviews, int score, List<Category> categories) {
        this.name = name;
        this.reviews = reviews;
        this.score = score;
        this.categories = categories;
    }

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "GUI.RestaurantPage.Restaurant{" +
                "name='" + name + '\'' +
                ", reviews=" + reviews +
                ", score=" + score +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant that)) return false;
        return score == that.score && name.equals(that.name) && reviews.equals(that.reviews) && categories.equals(that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, reviews, score, categories);
    }
}