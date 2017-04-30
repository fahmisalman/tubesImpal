/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.LoginAdmin;
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
public class ControlLoginAdmin implements ActionListener {

    LoginAdmin login;

    ResultSet rs;
    Connection conn;
    Statement stat;

    public ControlLoginAdmin() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        login = new LoginAdmin();
        login.setVisible(true);
        login.AddListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == login.getBtnLogin()) {
            try {
                String username = login.getTeksUsername().getText();
                String password = login.getTeksPassword().getText();
                String query = "select * from admin where username = '" + username + "' and password = '" + password + "'";
                rs = stat.executeQuery(query);
                int id = 0;
                String user, pass, nama = null;
                while (rs.next()) {
                    id = rs.getInt("id");
                    user = rs.getString("username");
                    pass = rs.getString("password");
                }
                if (id != 0) {
                    new ControlMenuAdmin();
                    login.dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControlLoginAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == login.getBtnKembali()) {
            new ControlHalamanUtama();
            login.dispose();
        }
    }
}
