import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner {
   @Test(priority = 1,enabled = true)
    public void doLogin() throws IOException, ConfigurationException {
        UserController controller = new UserController();
        controller.login("admin@roadtocareer.net","1234");

    }

    @Test(priority = 2)
    public void searchUser() throws IOException {
        UserController controller = new UserController();
        JsonPath jsonPath =controller.searchUser("6465");
        String message = jsonPath.get("message");
        System.out.println(message);
//        Assert.assertTrue(message.contains("User found"));

    }
@Test(priority = 3, description = "create new user")
    public void createUser() throws IOException {
    UserController controller = new UserController();
    UserModel model = new UserModel();
    model.setName("rajaul islam");
    model.setEmail("rest.assure12@gmail.com");
    model.setPassword("1234");
    model.setPhone_number("01622519799");
    model.setNid("123645678");
    model.setRole("admin");
    controller.createUser(model);

    }
}
