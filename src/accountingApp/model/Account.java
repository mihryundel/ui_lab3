package accountingApp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {

    private StringProperty name;
    private StringProperty description;
    private IntegerProperty balance;

    public Account() {
        this("","",0);
    }

    public Account(String name, String description, Integer balance) {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.balance = new SimpleIntegerProperty(balance);
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getBalance() {
        return balance.get();
    }

    public IntegerProperty balanceProperty() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance.set(balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "name=" + name +
                ", description=" + description +
                ", balance=" + balance +
                '}';
    }
}
