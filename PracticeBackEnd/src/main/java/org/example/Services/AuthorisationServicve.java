package org.example.Services;

import org.example.Special.PostgreSQLSpace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthorisationServicve {
    private String username;
    private String password;
    private PostgreSQLSpace postgres;

    public AuthorisationServicve(String username, String password) {
        this.username = username;
        this.password = password;
        postgres = new PostgreSQLSpace();
    }

    public String getToken(){
        String token = null;
        String SqlQueryGetToken = String.format("SELECT token FROM users " +
                "WHERE login = '%s' AND password = '%s';", username, password);
        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SqlQueryGetToken);

            while (resultSet.next()){

                token = resultSet.getString("token");

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }


        return token;
    }
}
