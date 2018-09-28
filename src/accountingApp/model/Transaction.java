package accountingApp.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Transaction {

    private StringProperty inputAccount;
    private StringProperty outputAccount;
    private IntegerProperty value;
    private StringProperty description;
    private ObjectProperty<LocalDate> date;
    private StringProperty type;
    private StringProperty category;

    public Transaction() {
        this("", "", 0, "", null, "", "");
    }

    public Transaction(String inputAccount, String outputAccount, Integer value, String description, LocalDate date, String type, String category) {
        this.inputAccount = new SimpleStringProperty(inputAccount);
        this.outputAccount = new SimpleStringProperty(outputAccount);
        this.value = new SimpleIntegerProperty(value);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.type = new SimpleStringProperty(type);
        this.category = new SimpleStringProperty(category);
    }

    public String getInputAccount() {
        return inputAccount.get();
    }

    public StringProperty inputAccountProperty() {
        return inputAccount;
    }

    public void setInputAccount(String inputAccount) {
        this.inputAccount.set(inputAccount);
    }

    public String getOutputAccount() {
        return outputAccount.get();
    }

    public StringProperty outputAccountProperty() {
        return outputAccount;
    }

    public void setOutputAccount(String outputAccount) {
        this.outputAccount.set(outputAccount);
    }

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
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

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "inputAccount=" + inputAccount +
                ", outputAccount=" + outputAccount +
                ", value=" + value +
                ", description=" + description +
                ", date=" + date +
                ", type=" + type +
                ", category=" + category +
                '}';
    }
}
