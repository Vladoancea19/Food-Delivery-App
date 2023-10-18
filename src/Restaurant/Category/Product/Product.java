package Restaurant.Category.Product;

import java.util.Objects;

public class Product {
    private String name;
    private String ingredients;
    private float price;
    private int weight;

    public Product(String name, String ingredients, float price, int weight) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.weight = weight;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Float.compare(product.price, price) == 0 && weight == product.weight && name.equals(product.name) && ingredients.equals(product.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients, price, weight);
    }
}
