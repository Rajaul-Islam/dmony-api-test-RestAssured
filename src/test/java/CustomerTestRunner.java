import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CustomerTestRunner extends SetUp {
    @Test(priority = 1,description = "Customer login successful")
    public void doLogin() throws ConfigurationException, IOException {
        UserController userController = new UserController();
        JsonPath jsonObj = userController.login(prop.getProperty("customer1Email"), prop.getProperty("customer1Password"));

        String messageActual = jsonObj.get("message");
        String messageExpected = "Login successfully";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 2, description = "Customer can withdraw successfully from agent")
    public void withdrawMoneyToAgent() throws IOException {
        String from_account = prop.getProperty("customer1Phone");
        String to_account = prop.getProperty("agentPhone");
        String amount = "500";

        UserController userController = new UserController();
        UserModel model = new UserModel();
        model.setFormAccount(from_account);
        model.setToAccount(to_account);
        model.setAmount(amount);
        JsonPath jsonObj = userController.withdraw(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Withdraw successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 3, description = "Customer can send money successfully to another customer")
    public void sendMoneyTOAnotherCustomer() throws IOException {
        String from_account = prop.getProperty("customer1Phone");
        String to_account = prop.getProperty("customer2Phone");
        String amount = "2000";

        UserController userController = new UserController();
        UserModel model = new UserModel();
        model.setFormAccount(from_account);
        model.setToAccount(to_account);
        model.setAmount(amount);
        JsonPath jsonObj = userController.sendMoney(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Send money successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
}
