/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MenuAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fahminurfikri
 */
public class ControlMenuAdmin implements ActionListener{

    private MenuAdmin menuAdmin;
    
    public ControlMenuAdmin() {
        menuAdmin = new MenuAdmin();
        menuAdmin.setVisible(true);
        menuAdmin.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == menuAdmin.getBtnAkun()) {
            try {
                new ControlKelolaAkun();
                menuAdmin.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == menuAdmin.getBtnLogout()) {
            try {
                new ControlLoginAdmin();
                menuAdmin.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == menuAdmin.getBtnLokasi()) {
            try {
                new ControlKelolaPaketWisata();
                menuAdmin.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }    
}
