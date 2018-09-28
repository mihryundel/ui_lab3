package accountingApp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExpenceCategory {

    private StringProperty name;
    private IntegerProperty sum;

    public ExpenceCategory() {
        this("", 0);
    }

    public ExpenceCategory(String name, Integer sum) {
        this.name = new SimpleStringProperty(name);
        this.sum = new SimpleIntegerProperty(sum);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getSum() {
        return sum.get();
    }

    public IntegerProperty sumProperty() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum.set(sum);
    }

    @Override
    public String toString() {
        return "ExpenceCategory{" +
                "name=" + name +
                ", sum=" + sum +
                '}';
    }
}
