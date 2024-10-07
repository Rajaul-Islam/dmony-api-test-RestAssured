import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner {
   @Test(priority = 1,enabled = true)
    public void doLogin() throws IOException, ConfigurationException {
        UserController controller = new UserController();
        controller.doLoginAsAdmin("admin@roadtocareer.net","1234");

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
    public void createUser() throws IOException, ConfigurationException {
    Faker faker = new Faker();

    UserController controller = new UserController();
    UserModel model = new UserModel();
    model.setName(faker.name().fullName());
    model.setEmail(faker.internet().emailAddress().toLowerCase());
    String phoneNumber = "01502"+Utils.generateRandomId(100000,999999);
    model.setPassword("1234");
    model.setPhone_number(phoneNumber);
    model.setNid(String.valueOf(Utils.generateRandomId(100000000,999999999)));
    model.setRole("admin");
   JsonPath jsonPath=  controller.createUser(model);
   int userId = jsonPath.get("user.id");
    System.out.println(userId);
   Utils.setEnvVariable("userId", String.valueOf(userId));
    }


}
