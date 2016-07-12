
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class sdftest {
    public static void main(String[] args) {
        
        try {
            Timestamp t;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            
            Date dta = sdf.parse("2004-08-13 14:44:31.0");
            t = new Timestamp(dta.getTime());
            System.out.print(t);
        } catch (ParseException ex) {
            Logger.getLogger(sdftest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
