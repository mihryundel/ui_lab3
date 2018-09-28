package accountingApp.controller;

import accountingApp.Main;
import accountingApp.model.ExpenceCategory;
import accountingApp.model.IncomeCategory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CategoryOverviewController {

    @FXML
    private TableView<IncomeCategory> incomeCategoryTable;
    @FXML
    private TableColumn<IncomeCategory, String> incomeCategoryNameColumn;
    @FXML
    private TableColumn<IncomeCategory, Integer> incomeCategorySumColumn;

    @FXML
    private TableView<ExpenceCategory> expenseCategoryTable;
    @FXML
    private TableColumn<ExpenceCategory, String> expenceCategoryNameColumn;
    @FXML
    private TableColumn<ExpenceCategory, Integer> expenceCategorySumColumn;

    private Main main;

    public CategoryOverviewController() {
    }

    @FXML
    private void initialize() {
        incomeCategoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        incomeCategorySumColumn.setCellValueFactory(cellData -> cellData.getValue().sumProperty().asObject());
        expenceCategoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        expenceCategorySumColumn.setCellValueFactory(cellData -> cellData.getValue().sumProperty().asObject());
    }

    public void setMain(Main main) {
        this.main = main;
        incomeCategoryTable.setItems(main.getIncomeCategoriesData());
        expenseCategoryTable.setItems(main.getExpenceCategoriesData());
    }

    @FXML
    private void handleDeleteIncomeCategory() {
        int selectedIndex = incomeCategoryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            incomeCategoryTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No income category selected");
            alert.setContentText("Please select an income category in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewIncomeCategory() {
        IncomeCategory tempIncome = new IncomeCategory();
        tempIncome.setSum(0);
        boolean okClicked = main.showIncomeCategoryEditDialog(tempIncome);
        if (okClicked) {
            main.getIncomeCategoriesData().add(tempIncome);
        }
    }

    @FXML
    private void handleEditIncomeCategory() {
        IncomeCategory selectedIncome = incomeCategoryTable.getSelectionModel().getSelectedItem();
        if (selectedIncome != null) {
            boolean okClicked = main.showIncomeCategoryEditDialog(selectedIncome);
            if (okClicked) {
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No income category selected");
            alert.setContentText("Please select an income category in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteExpenceCategory() {
        int selectedIndex = incomeCategoryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            expenseCategoryTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No expence category selected");
            alert.setContentText("Please select an expence category in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewExpenceCategory() {
        ExpenceCategory tempExpence = new ExpenceCategory();
        tempExpence.setSum(0);
        boolean okClicked = main.showExpenceCategoryEditDialog(tempExpence);
        if (okClicked) {
            main.getExpenceCategoriesData().add(tempExpence);
        }
    }

    @FXML
    private void handleEditExpenceCategory() {
        ExpenceCategory selectedIncome = expenseCategoryTable.getSelectionModel().getSelectedItem();
        if (selectedIncome != null) {
            boolean okClicked = main.showExpenceCategoryEditDialog(selectedIncome);
            if (okClicked) {
//                show(selectedIncome);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No income category selected");
            alert.setContentText("Please select an income category in the table.");

            alert.showAndWait();
        }
    }
}
