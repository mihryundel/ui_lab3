package accountingApp.controller;

import accountingApp.model.ExpenceCategory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ExpenceCategoryEditDialogController {

    @FXML
    private TextField expenceCategoryNameTextField;
    @FXML
    private TextField expenceCategorySumTextField;

    private Stage dialogStage;
    private ExpenceCategory expenceCategory;
    private boolean okClicked = false;

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setExpenceCategory(ExpenceCategory expenceCategory) {
        this.expenceCategory = expenceCategory;

        expenceCategoryNameTextField.setText(expenceCategory.getName());
        expenceCategorySumTextField.setText(Integer.toString(expenceCategory.getSum()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            expenceCategory.setName(expenceCategoryNameTextField.getText());
            expenceCategory.setSum(Integer.parseInt(expenceCategorySumTextField.getText()));

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

        if (expenceCategoryNameTextField.getText() == null || expenceCategoryNameTextField.getText().length() == 0) {
            errorMessage += "No valid expence category name!\n";
        }

        if (expenceCategorySumTextField.getText() == null || expenceCategorySumTextField.getText().length() == 0) {
            errorMessage += "No valid expence category sum!\n";
        } else {
            try {
                Integer.parseInt(expenceCategorySumTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid expence category sum (must be an integer)!\n";
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
