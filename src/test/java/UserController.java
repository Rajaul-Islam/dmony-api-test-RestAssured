import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController extends SetUp{

    public UserController() throws IOException {
        initConfig();
    }
    public void login(String email, String password
    ) throws ConfigurationException {
        RestAssured.baseURI= prop.getProperty("baseUrl");
        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res= given().contentType("application/json").body(model).post("/user/login");
        System.out.println("res full message: "+res.asString());
        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
        System.out.println(token);
        Utils.setEnvVariable("token",token);
    }


    public JsonPath searchUser(String userId){

        RestAssured.baseURI="http://dmoney.roadtocareer.net";
        Response res = given().contentType("application/json").header("Authorization", prop.get("token"))
                .when().get("/user/search/id/2223"+userId);
        System.out.println("searchUser = "+res.asString());
        return  res.jsonPath();
    }


public JsonPath createUser(UserModel model){
    RestAssured.baseURI= prop.getProperty("baseUrl");

    model.setEmail(model.getEmail());
    model.setPassword(model.getPassword());
    model.setName(model.getName());
    model.setPhone_number(model.getPhone_number());
    model.setNid(model.getNid());
    model.setRole(model.getRole());
    Response res = given().contentType("application/json").header("Authorization", prop.get("token"))
            .header("X-Auth-Secret-Key","ROADTOSDET")
            .when().post("/user/create");
    System.out.println("CREATE USER = "+res.asString());

    return  res.jsonPath();

}



}
