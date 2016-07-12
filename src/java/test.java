
import com.dao.RegisterPatientDAO;
import com.model.Patient;
import java.sql.Timestamp;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class test {

    public static void main(String args[]) {
        java.util.Date utilDate = new java.util.Date();
        Timestamp t = new Timestamp(System.currentTimeMillis());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("utilDate: " + utilDate);
        //System.out.println("sqlDate: " + sqlDate + " " + utilDate.getHours());
        System.out.println(t);
        /*Patient patient = new Patient();
        patient.setPatientid(1);
        patient.setFirstname("blech");
        patient.setMiddlename("middlename");
        patient.setLastname("lastname");
        patient.setRegionid("ncr");
        patient.setCityprovinceid("ncr0001");
        patient.setSpecificaddress("Batongbakal Brgy.");
        GregorianCalendar d = new GregorianCalendar(2001,0,20);
        System.out.print(new java.sql.Date(d.getTime().getTime()));
        patient.setBirthday(d);
        patient.setSex('M');
        
        RegisterPatientDAO rgp = new RegisterPatientDAO();
        
        rgp.addPatient(patient);*/
        
    }
}
