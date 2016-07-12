/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.ConsultDAO;
import com.dao.PatientMedicineDAO;
import com.dao.SearchPatientDAO;
import com.dao.UserNotesDAO;
import com.model.AdmissionRecord;
import com.model.ConsultTicket;
import com.model.ContactPerson;
import com.model.MedicalHistory;
import com.model.Patient;
import com.model.PatientMedicine;
import com.model.UserNote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ShowPatientDetails extends BaseServlet {

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
        
        /*if(request.getSession().getAttribute("rights") == null)
            response.sendRedirect("/InnerCoreProject/login.jsp");*/
        
        ServletContext context = getServletContext();
        int patientid;
        
        patientid =  Integer.parseInt(request.getParameter("patientid"));
        
        SearchPatientDAO spdao = new SearchPatientDAO();
        UserNotesDAO undao = new UserNotesDAO();
        PatientMedicineDAO pmdao = new PatientMedicineDAO();
        ConsultDAO cdao = new ConsultDAO();
        
        Patient p = new Patient();
        ArrayList<AdmissionRecord> records = new ArrayList<AdmissionRecord>();
        ArrayList<ContactPerson> contacts = new ArrayList<ContactPerson>();
        MedicalHistory medicalhistory = new MedicalHistory();
        ArrayList<PatientMedicine> patientmedicines = new ArrayList<PatientMedicine>();
        ArrayList<UserNote> nursenotes = new ArrayList<UserNote>();
        ArrayList<UserNote> doctornotes = new ArrayList<UserNote>();
        ArrayList<ConsultTicket> consultationHistory = new ArrayList<ConsultTicket>();
        
        p = spdao.getPatient(patientid);
        records = spdao.searchPatientRecord(p.getPatientid());
        contacts = spdao.getContactPersons(p.getPatientid());
        medicalhistory = spdao.getMedicalHistory(p.getPatientid());
        patientmedicines = pmdao.getPatientMedicine(p.getPatientid());
        consultationHistory = cdao.getConsultationHistory(patientid);
        
        nursenotes = undao.getNurseNotes(p.getPatientid());
        doctornotes = undao.getDoctorNotes(p.getPatientid());
        
        request.setAttribute("patient", p);
        request.setAttribute("records", records);
        request.setAttribute("contacts", contacts);
        request.setAttribute("medicalhistory", medicalhistory);
        request.setAttribute("patientmedicines", patientmedicines);
        request.setAttribute("nursenotes", nursenotes);
        request.setAttribute("doctornotes", doctornotes);
        request.setAttribute("consultationHistory", consultationHistory);
        
        RequestDispatcher rd = context.getRequestDispatcher("/patientdetails.jsp");
        rd.forward(request, response);
    }

}
