package com.jawwad.jdbc;
import org.postgresql.Driver;

import java.sql.*;
import java.util.Properties;

public class JdbcTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbctest";
        Connection con = null;
        try{
            Driver driver = new Driver();
            Properties properties = new Properties();
            properties.setProperty("user","postgres");
            properties.setProperty("password","123456");
            con = driver.connect(url,properties);
            Statement select =
                    con.createStatement();
            ResultSet resultSet =
                    select.executeQuery
                            ("select * from table_food");
            System.out.println(resultSet);
            while(resultSet.next()){
                System.out.println(resultSet.getString(7));
            }
            System.out.println(DriverManager.getDrivers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
