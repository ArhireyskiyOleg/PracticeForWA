package org.example.Special;

public class PostgreSQLSpace {
    private final String passsword = "1234";
    private final String postgresUser = "postgres";
    private final String urlAdress = "jdbc:postgresql://127.0.0.1:5432/NewsDB";


    public String getPasssword() {
        return passsword;
    }

    public String getPostgresUser() {
        return postgresUser;
    }

    public String getUrlAdress() {
        return urlAdress;
    }
}
