/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import View.MenuMember;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fahminurfikri
 */
public class ControlMenuMember implements ActionListener{
    private MenuMember menu;
    private Customer customer;

    public ControlMenuMember()  {
        menu = new MenuMember();
        menu.setVisible(true);
        menu.addListener(this);
        customer = new Customer();
        System.out.println(customer.getPassword());
        menu.getUserLabel().setText(customer.getNama());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == menu.getBtnPesan()) {
            try {
                new ControlPesanPaket();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuMember.class.getName()).log(Level.SEVERE, null, ex);
            }
            menu.dispose();
        } else if(event == menu.getBtnPembayaran()) {
            try {
                new ControlPembayaran();
                menu.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == menu.getBtnBatalPesan()) {
            try {
                new ControlBatalPesan();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuMember.class.getName()).log(Level.SEVERE, null, ex);
            }
            menu.dispose();
        } else if (event == menu.getBtnLogout()) {
            try {
                new ControlLoginMember();
                menu.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == menu.getBtnDaftarTransaksi()) {
            try {
                new ControlDaftarTransaksi();
                menu.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlMenuMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
