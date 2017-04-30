
import Controller.ControlHalamanUtama;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fahminurfikri
 */
public class Driver {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        Class.forName("org.gjt.mm.mysql.Driver");
        new ControlHalamanUtama();
        
    }

}
