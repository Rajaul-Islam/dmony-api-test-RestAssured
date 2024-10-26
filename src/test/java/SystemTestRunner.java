import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SystemTestRunner extends SetUp{
    @Test(priority = 1,description = "system can successfully deposit money to agent")
    public void addMoneyToAgent() throws IOException {
        String from_account = "System";
        String to_account = prop.getProperty("agentPhone");
        String amount = "10000";

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
