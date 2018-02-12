package nu.te4.beans;

import com.mysql.jdbc.Connection;
import nu.te4.utilitis.ConnectionFactory;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Stateless
public class RecipeBean {
    public JsonArray getRecipes() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM recipes";
        ResultSet res = stmt.executeQuery(query);

        JsonArrayBuilder recipes = Json.createArrayBuilder();
        while (res.next()){
            JsonObject obj = Json.createObjectBuilder().add("name", res.getString("name"))
                    .add("description", res.getString("description"))
                    .add("instruction",res.getString("instruction"))
                    .add("time", res.getString("time"))
                    .add("portion", res.getInt("portion"))
                    .add("category", res.getString("category"))
                    .add("id", res.getInt("id"))
                    .add("userID", res.getInt("user_id"))
                    .add("review", res.getDouble("review"))
                    .add("pictureURL", res.getString("picture_url")).build();
            recipes.add(obj);
        }
        return recipes.build();
    }
}
