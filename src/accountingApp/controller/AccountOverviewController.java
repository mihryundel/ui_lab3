package accountingApp.controller;

import accountingApp.Main;
import accountingApp.model.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccountOverviewController {

    @FXML
    private TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, String> accountNameColumn;
    @FXML
    private TableColumn<Account, Integer> accountBalanceColumn;

    @FXML
    private Label accountNameLabel;
    @FXML
    private Label accountBalanceLabel;
    @FXML
    private Label accountDescriptionLabel;

    private Main main;

    public AccountOverviewController() {
    }

    @FXML
    private void initialize() {
        accountNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        accountBalanceColumn.setCellValueFactory(cellData -> cellData.getValue().balanceProperty().asObject());

        showAccountDetails(null);

        accountTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAccountDetails(newValue));
    }

    public void setMain(Main main) {
        this.main = main;

        accountTable.setItems(main.getAccountsData());
    }

    private void showAccountDetails(Account account) {
        if (account != null) {
            accountNameLabel.setText(account.getName());
            accountBalanceLabel.setText(Integer.toString(account.getBalance()));
            accountDescriptionLabel.setText(account.getDescription());
        } else {
            accountNameLabel.setText("");
            accountBalanceLabel.setText("");
            accountDescriptionLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteAccount() {
        int selectedIndex = accountTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            accountTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No account selected");
            alert.setContentText("Please select an account in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewAccount() {
        Account tempAccount = new Account();
        boolean okClicked = main.showAccountEditDialog(tempAccount);
        if (okClicked) {
            main.getAccountsData().add(tempAccount);
        }
    }

    @FXML
    private void handleEditAccount() {
        Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            boolean okClicked = main.showAccountEditDialog(selectedAccount);
            if (okClicked) {
                showAccountDetails(selectedAccount);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select an account in the table.");

            alert.showAndWait();
        }
    }
}
