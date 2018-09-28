package accountingApp.controller;

import accountingApp.Main;
import javafx.fxml.FXML;

public class RootLayoutController {
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleAccount() {
        main.showAccountOverview();
    }

    @FXML
    private void handleCategory() {
        main.showCategoryOverview();
    }

    @FXML
    private void handleTransaction() {
        main.showTransactionOverview();
    }
}
