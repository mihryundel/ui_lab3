package accountingApp.controller;

import accountingApp.model.IncomeCategory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IncomeCategoryEditDialogController {

    @FXML
    private TextField incomeCategoryNameTextField;
    @FXML
    private TextField incomeCategorySumTextField;

    private Stage dialogStage;
    private IncomeCategory incomeCategory;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;

        incomeCategoryNameTextField.setText(incomeCategory.getName());
        incomeCategorySumTextField.setText(Integer.toString(incomeCategory.getSum()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            incomeCategory.setName(incomeCategoryNameTextField.getText());
            incomeCategory.setSum(Integer.parseInt(incomeCategorySumTextField.getText()));

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

        if (incomeCategoryNameTextField.getText() == null || incomeCategoryNameTextField.getText().length() == 0) {
            errorMessage += "No valid income category name!\n";
        }

        if (incomeCategorySumTextField.getText() == null || incomeCategorySumTextField.getText().length() == 0) {
            errorMessage += "No valid income category sum!\n";
        } else {
            try {
                Integer.parseInt(incomeCategorySumTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid income category sum (must be an integer)!\n";
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
