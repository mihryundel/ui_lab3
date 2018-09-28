package accountingApp.controller;

import accountingApp.Main;
import accountingApp.model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AccountEditDialogController {

    @FXML
    private TextField accountNameTextField;
    @FXML
    private TextField accountBalanceTextField;
    @FXML
    private TextField accountDescriptionTextField;

    private Stage dialogStage;
    private Account account;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAccount(Account account) {
        this.account = account;

        accountNameTextField.setText(account.getName());
        accountBalanceTextField.setText(Integer.toString(account.getBalance()));
        accountDescriptionTextField.setText(account.getDescription());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            account.setName(accountNameTextField.getText());
            account.setBalance(Integer.parseInt(accountBalanceTextField.getText()));
            account.setDescription(accountDescriptionTextField.getText());

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

        if (accountNameTextField.getText() == null || accountNameTextField.getText().length() == 0) {
            errorMessage += "No valid account name!\n";
        }
        if (accountDescriptionTextField.getText() == null || accountDescriptionTextField.getText().length() == 0) {
            errorMessage += "No valid description!\n";
        }

        if (accountBalanceTextField.getText() == null || accountBalanceTextField.getText().length() == 0) {
            errorMessage += "No valid account balance!\n";
        } else {
            try {
                Integer.parseInt(accountBalanceTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid account balance (must be an integer)!\n";
            }
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
