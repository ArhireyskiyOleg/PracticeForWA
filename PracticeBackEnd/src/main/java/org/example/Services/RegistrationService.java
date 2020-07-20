package org.example.Services;

import org.example.Special.PostgreSQLSpace;

import java.sql.*;
import java.util.UUID;

public class RegistrationService {

    private String username;
    private String password;
    private String firstname;
    private String secondname;
    private  PostgreSQLSpace postgres;

    public RegistrationService(String username, String password, String firstname, String secondname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.secondname = secondname;
        postgres = new PostgreSQLSpace();
    }

    public void RegistrationUser() {
        if(!HasRegistration()){
            int idUser = GetId();
            String token = CreateToken();
            String SqlQuery = String.format("INSERT INTO public.users(\n" +
                    "\tid_user, login, firstname, secondname, token, password)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?);");
            try (Connection conn = DriverManager.getConnection(
                    postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
                 PreparedStatement statement = conn.prepareStatement(SqlQuery)){
                     statement.setInt(1, idUser);
                     statement.setString(2, username);
                     statement.setString(3, firstname);
                     statement.setString(4, secondname);
                     statement.setString(5, token);
                     statement.setString(6, password);
                     if(statement.executeUpdate() > 0) System.err.println("User registry succeseful");
                     else System.err.println("User registry failed");
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
            }
        }
    }

    private String CreateToken() {
        return UUID.randomUUID() + "-jwt-" + username;
    }

    private int GetId() {
        int id = 0;
        String SqlQueryCount = "SELECT COUNT(id_user) FROM users";
        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SqlQueryCount);

            while (resultSet.next()){

                int count = resultSet.getInt("count") + 1;

            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return id;
    }

    private boolean HasRegistration() {
        String SqlQueryCount = String.format("SELECT COUNT(id_user) FROM users WHERE login = %s", username);
        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SqlQueryCount);

            int count = 0;

            while (resultSet.next()){

                count = resultSet.getInt("count");

            }
            if (count == 0) return false;

        } catch (Exception e){
           return false;
        }
        return true;
    }

}
