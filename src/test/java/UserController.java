import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserController extends SetUp{

    public UserController() throws IOException {
        initConfig();
    }

    public JsonPath doLoginAsAdmin(String email, String password) throws org.apache.commons.configuration.ConfigurationException {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res = given().contentType("application/json").body(model).when().post("/user/login")
                .then().assertThat().statusCode(200).extract().response();

        System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.get("token");
        System.out.println(token);
        Utils.setEnvVariable("token", token);
        return res.jsonPath();
    }

    public JsonPath login(String email, String password
    ) throws ConfigurationException {
        RestAssured.baseURI= prop.getProperty("baseUrl");
        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res= given().contentType("application/json").body(model).when().post("/user/login")
                .then().assertThat().statusCode(200).extract().response();
        System.out.println("res full message: "+res.asString());
        JsonPath jsonPath = res.jsonPath();
        String token = jsonPath.get("token");
        System.out.println(token);
        Utils.setEnvVariable("token",token);
        return jsonPath;
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


    public JsonPath depositToAgent(UserModel model) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                .when().post("transaction/deposit");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath withdraw(UserModel model) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                .when().post("transaction/withdraw");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath sendMoney(UserModel model) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                .when().post("transaction/sendMoney");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath payment(UserModel model) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                .when().post("transaction/payment");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath checkBalance(String phoneNumber) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        Response res = given().contentType("application/json")
                .header("Authorization", prop.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                .when().get("transaction/balance/" + phoneNumber);
        System.out.println(res.asString());
        return res.jsonPath();
    }



}
