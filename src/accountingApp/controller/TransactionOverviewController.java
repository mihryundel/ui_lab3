package accountingApp.controller;

import accountingApp.Main;
import accountingApp.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransactionOverviewController {

    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> transactionInputAccountColumn;
    @FXML
    private TableColumn<Transaction, String> transactionOutputAccountColumn;

    @FXML
    private Label transactionInputAccountLabel;
    @FXML
    private Label transactionOutputAccountLabel;
    @FXML
    private Label transactionValueLabel;
    @FXML
    private Label transactionDescriptionLabel;
    @FXML
    private Label transactionDateLabel;
    @FXML
    private Label transactionTypeLabel;
    @FXML
    private Label transactionCategoryLabel;

    private Main main;

    public TransactionOverviewController() {
    }

    @FXML
    private void initialize() {
        transactionInputAccountColumn.setCellValueFactory(cellData -> cellData.getValue().inputAccountProperty());
        transactionOutputAccountColumn.setCellValueFactory(cellData -> cellData.getValue().outputAccountProperty());

        showTransactionDetails(null);

        transactionTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTransactionDetails(newValue));
    }

    public void setMain(Main main) {
        this.main = main;

        transactionTable.setItems(main.getTransactionsData());
    }

    private void showTransactionDetails(Transaction transaction) {
        if (transaction != null) {
            transactionInputAccountLabel.setText(transaction.getInputAccount());
            transactionOutputAccountLabel.setText(transaction.getOutputAccount());
            transactionValueLabel.setText(Integer.toString(transaction.getValue()));
            transactionDescriptionLabel.setText(transaction.getDescription());
            transactionDateLabel.setText(transaction.getDate().toString());
            transactionTypeLabel.setText(transaction.getType());
            transactionCategoryLabel.setText(transaction.getCategory());
        } else {
            transactionInputAccountLabel.setText("");
            transactionOutputAccountLabel.setText("");
            transactionValueLabel.setText("");
            transactionDescriptionLabel.setText("");
            transactionDateLabel.setText("");
            transactionTypeLabel.setText("");
            transactionCategoryLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteTransaction() {
        int selectedIndex = transactionTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            transactionTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No transaction selected");
            alert.setContentText("Please select a transaction in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewTransaction() {
        Transaction tempTransaction = new Transaction();
        boolean okClicked = main.showTransactionEditDialog(tempTransaction);
        if (okClicked) {
            main.getTransactionsData().add(tempTransaction);
        }
    }

    @FXML
    private void handleEditTransaction() {
        Transaction selectedTransaction = transactionTable.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            boolean okClicked = main.showTransactionEditDialog(selectedTransaction);
            if (okClicked) {
                showTransactionDetails(selectedTransaction);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Transaction Selected");
            alert.setContentText("Please select a transaction in the table.");

            alert.showAndWait();
        }
    }
}
