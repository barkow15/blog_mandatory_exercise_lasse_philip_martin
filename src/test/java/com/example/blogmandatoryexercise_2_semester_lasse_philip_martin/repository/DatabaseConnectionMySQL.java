package com.example.blogmandatoryexercise_2_semester_lasse_philip_martin.repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

public class DatabaseConnectionMySQL {
    Connection conn = null;
    private boolean debug = true;


    public DatabaseConnectionMySQL() throws SQLException, ClassNotFoundException {
        // Loader databasedriveren
        Class.forName("com.mysql.cj.jdbc.Driver");

        // DB forbindelsesoplysninger
        String username = "blogdat18a";
        String password = "Wk900N?9_pR7";
        String server   = "den1.mysql5.gear.host";
        String dbName   = "blogdat18a";
        String port     = "3306";
        this.conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + dbName, username, password);
    }


    // Metode som ekskverer MySQL statements
    private int executeSql(String sql)
    {
        int returnvalue = -1;
        PreparedStatement pstmt = null;


        try
        {
            if(this.debug) System.out.println("DB CONNECTION OPENED");

            pstmt = conn.prepareStatement(sql);

            if(this.debug) System.out.println("executeSql: EXECUTING SQL ... " + sql);
            returnvalue = pstmt.executeUpdate();

            if(this.debug) System.out.println("SQL SUCCESS: " + returnvalue);
            //System.out.println(returnvalue);
            if(this.debug) System.out.println("CLOSING DB CONNECTION ...");

            // Vi lader være med at lukke forbindelsen
            /*
            if(conn != null)
            {
                conn.close();
                if(this.debug) System.out.println("DB CONNECTION CLOSED");
            }
            */

        }
        catch (Exception e)
        {
            System.err.println("executeSql: " + e.getClass().getName() + ": " + e.getMessage() + " Sql: " + sql);

            returnvalue = -1;
            //System.exit(0);
        }
        return(returnvalue);
    }

    // Metode som forespørger på resultatset baseret på MySQL statements
    private ResultSet querySql(String sql)
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            if(this.debug) System.out.println("DB CONNECTION OPENED");

            pstmt = conn.prepareStatement(sql);

            if(this.debug) System.out.println("querySql EXECUTING SQL QUERY ... " + sql);
            rs = pstmt.executeQuery();

            return rs;
        }
        catch (Exception e)
        {
            System.err.println("querySql: " + e.getClass().getName() + ": " + e.getMessage() + " Sql: " + sql);
            System.exit(0);
        }
        return rs;
    }

    // Metode som lukker databaseforbindelsen
    private void closeConnection(ResultSet rs)
    {
        try
        {
            if(conn != null)
            {
                if(this.debug) System.out.println("CLOSING DB CONNECTION");
                rs.close();
                conn.close();
                if(this.debug) System.out.println("DB CONNECTION CLOSED");
            }
        }
        catch(Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}