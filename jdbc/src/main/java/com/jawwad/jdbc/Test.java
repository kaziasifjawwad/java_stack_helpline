package com.jawwad.jdbc;

import java.sql.*;

public class Test {
    private final static String
            url = "jdbc:postgresql://103.174.51.235:4400/unique_restaurant_management_system";
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try{
             connection =DriverManager.getConnection(url,"kaziasifjawwad","whysoserious");
            assert connection != null;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from table_food");
            resultSet.afterLast();;
            while (resultSet.previous()){
                String food_name = resultSet.getString("food_name");
                System.out.println(food_name);
            }
            resultSet.afterLast();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            assert connection != null;
            connection.close();
        }
    }
}
