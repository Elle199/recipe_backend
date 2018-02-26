package nu.te4.beans;

import com.mysql.jdbc.Connection;
import nu.te4.utilitis.ConnectionFactory;

import javax.ejb.Stateless;
import javax.json.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class RecipeBean {
    public JsonArray getRecipes() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM recipes");
        ResultSet res = stmt.executeQuery();

        JsonArrayBuilder recipes = Json.createArrayBuilder();
        while (res.next()) {
            JsonObject obj = Json.createObjectBuilder().add("name", res.getString("name"))
                    .add("description", res.getString("description"))
                    .add("instruction", res.getString("instruction"))
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

    public JsonObject getRecipe(int recipe_id) {
        ResultSet resultSet;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT ? FROM recipes");
            stmt.setInt(1, recipe_id);
            resultSet = stmt.executeQuery();
        }catch (Exception e){
            System.out.println("Error getting connection or executing query: ");
            resultSet = null;
            e.printStackTrace();
        }

        JsonObjectBuilder recipe = Json.createObjectBuilder();
        try{
            recipe.add("id", resultSet.getInt("id"));
            recipe.add("name", resultSet.getString("name"));
            recipe.add("description", resultSet.getString("description"));
            recipe.add("instruction", resultSet.getString("instruction"));
            recipe.add("time", resultSet.getString("time"));
            recipe.add("portion", resultSet.getInt("portion"));
            recipe.add("category", resultSet.getString("category"));
            recipe.add("userID", resultSet.getString("userID"));
            recipe.add("review", resultSet.getString("review"));
            recipe.add("pictureURL", resultSet.getString("pictureURL"));
        }catch (Exception e){
            System.out.println("Error building JsonObject: ");
            e.printStackTrace();
        }
        return recipe.build();
    }

    public void addRecipe() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO recipes () VALUES (?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, "");
        stmt.setString(2, "");
        stmt.setString(3, "");
        stmt.setString(4, "");
        stmt.setString(6, "");
        stmt.setString(10, "");
        stmt.setDouble(9,0);
        stmt.setInt(5,0);
        stmt.setInt(7,0);
        stmt.setInt(8,0);

        int rows = stmt.executeUpdate();
    }

    public void addIngredient(String name) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO ingredients () VALUE (?)");
        stmt.setString(1, name);
        stmt.executeUpdate();
    }
}
