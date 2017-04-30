/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import View.LoginMember;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fahminurfikri
 */
public class ControlLoginMember implements ActionListener{

    LoginMember login;
    Customer customer;
    
    ResultSet rs;
    Connection conn;
    Statement stat;
    
    public ControlLoginMember() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        login = new LoginMember();
        login.setVisible(true);
        login.addListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == login.getBtnLogin()) {
            try {
                String username = login.getjTextField1().getText();
                String password = login.getjPasswordField1().getText();
                String query = "select * from customer where username = '" + username + "' and password = '" + password + "'";
                rs = stat.executeQuery(query);
                int id = 0;
                String user, pass, nama = null;
                while (rs.next()) {
                    id = rs.getInt("idCustomer");
                    user = rs.getString("username");
                    pass = rs.getString("password");
                    nama = rs.getString("nama");
                }
                if (id != 0) {
                    customer = new Customer();
                    customer.setIdCustomer(id);
                    customer.setNama(nama);
                    customer.setUsername(username);
                    customer.setPassword(password);
                    new ControlMenuMember();
                    login.dispose();
                } else {
                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ControlLoginMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == login.getBtnKembali()) {
            new ControlHalamanUtama();
            login.dispose();
        }
    }
    
}
