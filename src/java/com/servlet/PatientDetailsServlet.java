/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.RegisterPatientDAO;
import com.model.ContactPerson;
import com.model.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PatientDetailsServlet extends BaseServlet {


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
        c.setContactpersonname(request.getParameter("contactpersonname"));
        c.setContactaddress(request.getParameter("contactpersonaddress"));
        c.setContactnumber(request.getParameter("contactpersonnumber"));
        c.setRelationship(request.getParameter("relationship"));
        c.setPartytopaybill(Boolean.valueOf(request.getParameter("ispartytopay")));
        
        RegisterPatientDAO rpdao = new RegisterPatientDAO();
        RequestDispatcher rd;
        SQLException e = null;
            
        try {
            p = rpdao.addPatient(p);
            c.setPatientid(p.getPatientid());
            rpdao.addContactPerson(c);
        }catch (SQLException ex) {
            Logger.getLogger(PatientDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            e = ex;
            request.setAttribute("error", "There is an error on registering patient");
            rd = context.getRequestDispatcher("/regpatient.jsp");
            rd.forward(request, response);
        }
        
        if(e==null){
        request.setAttribute("currentpatient", p);
        rd = context.getRequestDispatcher("/ShowPatientDetails?patientid="+p.getPatientid());
        rd.forward(request, response);
        }
        
    }

}
