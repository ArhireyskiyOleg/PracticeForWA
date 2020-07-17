package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPartNumber {
    private List<String> userList;
    private static final String SQL_SELECT = "SELECT "+
            "username " +
            "password " +
            "FROM public.parts;";

    public JDBCPartNumber(){
    }

    private void createPriceList(){
        userList = new ArrayList<>();

        PostgreSQLSpace postgres = new PostgreSQLSpace();

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_SELECT);

            while (resultSet.next()){

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                userList.add(username);

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public List<String> getPartNumber(){
        createPriceList();
        return userList;
    }
}
