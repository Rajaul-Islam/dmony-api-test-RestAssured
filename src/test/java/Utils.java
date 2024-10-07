import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Utils {
    public static void setEnvVariable(String key, String value) throws ConfigurationException {

        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key, value);
        config.save();

    }

    public static void saveUsers(UserModel usersModel) throws IOException, org.json.simple.parser.ParseException {
        String fileLocation = "./src/test/resources/users.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(fileLocation));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",usersModel.getName());
        jsonObject.put("email",usersModel.getEmail());
        jsonObject.put("password",usersModel.getPassword());
        jsonObject.put("phone_number",usersModel.getPhone_number());
        jsonObject.put("nid",usersModel.getNid());
        jsonObject.put("role",usersModel.getRole());

        jsonArray.add(jsonObject);

        FileWriter writer = new FileWriter(fileLocation);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }
    public static int generateRandomId(int min, int max){
        double randomId = Math.random()*(max-min)+min;
        int rand = (int) randomId;
        return rand;

    }
}

