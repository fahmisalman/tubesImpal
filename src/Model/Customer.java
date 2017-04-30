/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Guess
 */
public class Customer {

    private static String nama;
    private static int idCustomer;
    private static String username;
    private static String password;

    private static Connection conn;
    private static Statement stat;
    private static String query;
    private static ResultSet rs;

    public static void Customer(String nama, int idCustomer, String username, String password) throws SQLException {
        Customer.nama = nama;
        Customer.idCustomer = idCustomer;
        Customer.username = username;
        Customer.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        Customer.nama = nama;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        Customer.idCustomer = idCustomer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Customer.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Customer.password = password;
    }
    
    public void addCustomer() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        query = "insert into customer (nama, username, password) values ('" + nama + "', '" + username + "', '" + password + "')";
        stat.execute(query);
    }
    
    public void deleteCustomer(String nama) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        query = "delete from customer where nama = '" + nama + "'";
        stat.execute(query);
    }

}
