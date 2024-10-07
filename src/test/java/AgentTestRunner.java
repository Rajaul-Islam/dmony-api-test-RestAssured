import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

public class AgentTestRunner extends SetUp {
    @Test(priority = 1,description = "Agent login successful")
    public void doLogin() throws IOException, ConfigurationException {
        UserController userController = new UserController();
        JsonPath jsonObj = userController.login(prop.getProperty("agentEmail"), prop.getProperty("agentPassword"));

        String messageActual = jsonObj.get("message");
        System.out.println(messageActual);
        String messageExpected = "Login successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 2, description = "Agent can deposit money successfully")
    public void depositMoneyToCustomer() throws IOException, ParseException {
        String from_account = prop.getProperty("agentPhone");
        String to_account = prop.getProperty("customer1Phone");
        String amount = "8000";

        UserController userController = new UserController();
        UserModel model = new UserModel();
        model.setFormAccount(from_account);
        model.setToAccount(to_account);
        model.setAmount(amount);
        JsonPath jsonObj = userController.depositToAgent(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Deposit successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
}
