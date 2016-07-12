/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.RegisterPatientDAO;
import com.dao.UserDAO;
import com.model.AdmissionRecord;
import com.model.ContactPerson;
import com.model.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class AddPatient extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Patient p = new Patient();

        ServletContext context = getServletContext();
        p.setPatientid(Integer.parseInt(request.getParameter("patientid")));
        p.setFirstname(request.getParameter("firstname"));
        p.setMiddlename(request.getParameter("middlename"));
        p.setLastname(request.getParameter("lastname"));
        String selectedSex = request.getParameter("sex");
        if (selectedSex == null) {
            RequestDispatcher rd = context.getRequestDispatcher("/addpatient.jsp");
            rd.forward(request, response);
        } else if (selectedSex.equals("M")) {
            p.setSex('M');
        } else if (selectedSex.equals("F")) {
            p.setSex('F');
        }

        p.setBirthday(request.getParameter("birthdate"));
        p.setRegionid(request.getParameter("regionid"));
        p.setCityprovinceid(request.getParameter("cityprovinceid"));
        p.setSpecificaddress(request.getParameter("specificaddress"));
        p.setBirthplace(request.getParameter("birthplace"));
        p.setCellphonenum(request.getParameter("patientcontactnumber"));
        p.setOccupationname(request.getParameter("occupationname"));
        p.setOccupationaddress(request.getParameter("occupationaddress"));
        p.setOccupationlandline(request.getParameter("occupationlandline"));
        p.setNationality(request.getParameter("nationality"));
        p.setReligion(request.getParameter("religion"));
        p.setCivilstatus(request.getParameter("civilstatus"));
        p.setEducation(request.getParameter("education"));

        ContactPerson c = new ContactPerson();
        ContactPerson c2 = new ContactPerson();
        if (request.getParameter("contactpersonname2") != null) {
            c2.setContactpersonname(request.getParameter("contactpersonname2"));
            c2.setContactaddress(request.getParameter("contactpersonaddress2"));
            c2.setContactnumber(request.getParameter("contactpersonnumber2"));
            c2.setRelationship(request.getParameter("relationship2"));
            c2.setPartytopaybill(Boolean.valueOf(request.getParameter("ispartytopay2")));
        }
        c.setContactpersonname(request.getParameter("contactpersonname"));
        c.setContactaddress(request.getParameter("contactpersonaddress"));
        c.setContactnumber(request.getParameter("contactpersonnumber"));
        c.setRelationship(request.getParameter("relationship"));
        c.setPartytopaybill(Boolean.valueOf(request.getParameter("ispartytopay")));

        AdmissionRecord ar = new AdmissionRecord();
        ar.setDatetimeadmitted(new Timestamp(System.currentTimeMillis()));
        ar.setConditionid(request.getParameter("admitcondition"));
        ar.setAdmittingdiagnosis(request.getParameter("admitdiagnosis"));
        String patienttype = request.getParameter("patienttype");
        if (patienttype.equalsIgnoreCase("Inpatient")) {
            ar.setPatienttypeid("IA");
        } else {
            ar.setPatienttypeid("OA");
        }
        ar.setAdmittingnurse(Integer.parseInt(request.getParameter("admitnurse")));
        ar.setAdmittingphysician(Integer.parseInt(request.getParameter("admitphysician")));

        if (p.getFirstname() == null || p.getMiddlename() == null || p.getLastname() == null || p.getRegionid() == null || p.getCityprovinceid() == null || p.getSpecificaddress() == null || p.getBirthday() == null || ar.getAdmittingdiagnosis() == null) {
            RequestDispatcher rd = context.getRequestDispatcher("/addpatient.jsp");
            rd.forward(request, response);
        }

        RegisterPatientDAO registerPatientDAO = new RegisterPatientDAO();
        /*Patient successPatient = registerPatientDAO.addPatient(p);
         if (successPatient != null) {
         c.setPatientid(successPatient.getPatientid());
         if(!c2.getContactpersonname().isEmpty())
         c2.setPatientid(successPatient.getPatientid());
         }*/
        ar = registerPatientDAO.registerPatient(p, ar, c);

        RequestDispatcher rd = context.getRequestDispatcher("/ShowPatientDetails?patientid=" + ar.getPatientid());
        rd.forward(request, response);

    }

}
