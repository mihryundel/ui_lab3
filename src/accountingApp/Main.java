package accountingApp;

import accountingApp.controller.*;
import accountingApp.model.Account;
import accountingApp.model.ExpenceCategory;
import accountingApp.model.IncomeCategory;
import accountingApp.model.Transaction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Account> accountsData = FXCollections.observableArrayList();
    private ObservableList<ExpenceCategory> expenceCategoriesData = FXCollections.observableArrayList();
    private ObservableList<IncomeCategory> incomeCategoriesData = FXCollections.observableArrayList();
    private ObservableList<Transaction> transactionsData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AccountingApp");

        initRootLayout();

        showAccountOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setMain(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAccountOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/accountOverview.fxml"));
            AnchorPane accountOverview = (AnchorPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(accountOverview);

            AccountOverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCategoryOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/categoryOverview.fxml"));
            AnchorPane categoryOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(categoryOverview);

            CategoryOverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTransactionOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/transactionOverview.fxml"));
            AnchorPane transactionOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(transactionOverview);

            TransactionOverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Main() {

        accountsData.add(new Account("Account1", "Description1", 1));
        accountsData.add(new Account("Account2", "Description2", 2));
        accountsData.add(new Account("Account3", "Description3", 3));
        accountsData.add(new Account("Account4", "Description4", 4));
        accountsData.add(new Account("Account5", "Description5", 5));
        accountsData.add(new Account("Account6", "Description6", 6));

        incomeCategoriesData.add(new IncomeCategory("Salary", 100000));
        incomeCategoriesData.add(new IncomeCategory("Bonus", 10000));
        incomeCategoriesData.add(new IncomeCategory("Grant", 5000));
        incomeCategoriesData.add(new IncomeCategory("Material aid", 10000));

        expenceCategoriesData.add(new ExpenceCategory("Education", 40000));
        expenceCategoriesData.add(new ExpenceCategory("Food", 10000));
        expenceCategoriesData.add(new ExpenceCategory("Medicine", 5000));
        expenceCategoriesData.add(new ExpenceCategory("Clothes", 10000));
        expenceCategoriesData.add(new ExpenceCategory("Entertainment", 10000));

        transactionsData.add(new Transaction("Account1", "Account2", 1500, "Description", LocalDate.of(2018, 10, 10), "Income", "Grant"));
        transactionsData.add(new Transaction("Account2", "Account3", 150000, "Description", LocalDate.of(2018, 10, 5), "Income", "Salary"));
        transactionsData.add(new Transaction("Account4", "Account5", 25000, "Description", LocalDate.of(2018, 11, 15), "Income", "Bonus"));
        transactionsData.add(new Transaction("Account4", "Account1", 40000, "Description", LocalDate.of(2018, 5, 25), "Transition", "Education"));

    }

    public ObservableList<Account> getAccountsData() {
        return accountsData;
    }

    public ObservableList<IncomeCategory> getIncomeCategoriesData() {
        return incomeCategoriesData;
    }

    public ObservableList<ExpenceCategory> getExpenceCategoriesData() {
        return expenceCategoriesData;
    }

    public ObservableList<Transaction> getTransactionsData() {
        return transactionsData;
    }

    public boolean showAccountEditDialog(Account account) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/accountEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AccountEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccount(account);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showIncomeCategoryEditDialog(IncomeCategory incomeCategory) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/incomeCategoryEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Income Category");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            IncomeCategoryEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setIncomeCategory(incomeCategory);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showExpenceCategoryEditDialog(ExpenceCategory expenceCategory) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/expenceCategoryEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Expence Category");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ExpenceCategoryEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setExpenceCategory(expenceCategory);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showTransactionEditDialog(Transaction transaction) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/transactionEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Transaction");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            TransactionEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTransaction(transaction);
            controller.setMain(this);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
