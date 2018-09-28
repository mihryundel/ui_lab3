package accountingApp.controller;

import accountingApp.Main;
import accountingApp.model.Account;
import accountingApp.model.ExpenceCategory;
import accountingApp.model.IncomeCategory;
import accountingApp.model.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransactionEditDialogController {

    @FXML
    private ChoiceBox transactionInputAccountChoiceBox;
    @FXML
    private ChoiceBox transactionOutputAccountChoiceBox;
    @FXML
    private TextField transactionValueTextField;
    @FXML
    private DatePicker transactionDatePicker;
    @FXML
    private TextField transactionDescriptionTextField;
    @FXML
    private ChoiceBox transactionTypeChoiceBox;
    @FXML
    private ChoiceBox transactionCategoryChoiceBox;

    private Stage dialogStage;
    private Transaction transaction;
    private boolean okClicked = false;

    private Main main;

    public void setMain(Main main) {
        this.main = main;

        ObservableList<String> accounts = FXCollections.observableArrayList();
        for (Account account : main.getAccountsData()) {
            accounts.add(account.getName());
        }
        transactionInputAccountChoiceBox.setItems(accounts);
        transactionOutputAccountChoiceBox.setItems(accounts);

        ObservableList<String> categories = FXCollections.observableArrayList();
        for (IncomeCategory incomeCategory : main.getIncomeCategoriesData()) {
            categories.add(incomeCategory.getName());
        }
        for (ExpenceCategory expenceCategory : main.getExpenceCategoriesData()) {
            categories.add(expenceCategory.getName());
        }
        transactionCategoryChoiceBox.setItems(categories);

        ObservableList<String> types =
                FXCollections.observableArrayList(
                        "Приход",
                        "Расход",
                        "Перевод"
                );
        transactionTypeChoiceBox.setItems(types);
    }

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;

        transactionInputAccountChoiceBox.setValue(transaction.getInputAccount());
        transactionOutputAccountChoiceBox.setValue(transaction.getOutputAccount());
        transactionValueTextField.setText(Integer.toString(transaction.getValue()));
        transactionDatePicker.setValue(transaction.getDate());
        transactionDescriptionTextField.setText(transaction.getDescription());
        transactionTypeChoiceBox.setValue(transaction.getType());
        transactionCategoryChoiceBox.setValue(transaction.getCategory());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            transaction.setInputAccount(transactionInputAccountChoiceBox.getSelectionModel().getSelectedItem().toString());
            transaction.setOutputAccount(transactionOutputAccountChoiceBox.getSelectionModel().getSelectedItem().toString());
            transaction.setValue(Integer.parseInt(transactionValueTextField.getText()));
            transaction.setDate(transactionDatePicker.getValue());
            transaction.setDescription(transactionDescriptionTextField.getText());
            transaction.setType(transactionTypeChoiceBox.getSelectionModel().getSelectedItem().toString());
            transaction.setCategory(transactionCategoryChoiceBox.getSelectionModel().getSelectedItem().toString());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (transactionInputAccountChoiceBox.getValue() == null) {
            errorMessage += "No valid input account!\n";
        }
        if (transactionOutputAccountChoiceBox.getValue() == null) {
            errorMessage += "No valid output account!\n";
        }
        if (transactionDescriptionTextField.getText() == null || transactionDescriptionTextField.getText().length() == 0) {
            errorMessage += "No valid description!\n";
        }
        if (transactionDatePicker.getValue() == null || transactionDatePicker.getValue().getDayOfMonth() == 0) {
            errorMessage += "No valid date!\n";
        } else {
            try {
                transactionDatePicker.getValue();
            } catch (NumberFormatException e) {
                errorMessage += "Неверная дата(введите число)!\n";
            }
        }
        if (transactionValueTextField.getText() == null || transactionValueTextField.getText().length() == 0) {
            errorMessage += "No valid value!\n";
        } else {
            try {
                Integer.parseInt(transactionValueTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid valut(must be a number)!\n";
            }
        }
        if (transactionTypeChoiceBox.getValue() == null) {
            errorMessage += "No valid type!\n";
        }
        if (transactionCategoryChoiceBox.getValue() == null) {
            errorMessage += "No valid category!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
