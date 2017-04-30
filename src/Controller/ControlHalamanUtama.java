/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.HalamanUtama;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fahminurfikri
 */
public class ControlHalamanUtama implements ActionListener{
    
    HalamanUtama halamanUtama;
    
    public ControlHalamanUtama() {
        halamanUtama = new HalamanUtama();
        halamanUtama.setVisible(true);
        halamanUtama.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == halamanUtama.getBtnAdmin()) {
            try {
                new ControlLoginAdmin();
                halamanUtama.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlHalamanUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == halamanUtama.getBtnMember()) {
            try {
                new ControlLoginMember();
                halamanUtama.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlHalamanUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
