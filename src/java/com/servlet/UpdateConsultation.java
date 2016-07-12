/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.ConsultDAO;
import com.dao.PatientMedicineDAO;
import com.dao.SearchPatientDAO;
import com.model.ConsultTicket;
import com.model.Patient;
import com.model.PatientMedicine;
import com.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class UpdateConsultation extends BaseServlet {

    

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
        
        ConsultTicket ct = new ConsultTicket();
        ConsultDAO cdao = new ConsultDAO();
        PatientMedicineDAO pmdao = new PatientMedicineDAO();
        SearchPatientDAO sdao = new SearchPatientDAO();
        
        ArrayList<ConsultTicket> tickets = new ArrayList<ConsultTicket>();
        ArrayList<PatientMedicine> medicines = new ArrayList<PatientMedicine>();
        
        HttpSession session = request.getSession(false);
        User loggedInUser = (User) session.getAttribute("user");
        
        ct.setPatientid(Integer.parseInt(request.getParameter("patientid")));
        ct.setDateofconsultation(request.getParameter("datetoday"));
        ct.setConsultstatus(request.getParameter("statusid"));
        ct.setConsultedby(loggedInUser.getLicensenum());
        
        cdao.updateConsultation(ct);
        
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String currentDate = currentTime.toString().substring(0,10);
        
        Patient p = new Patient();
        p = sdao.getPatient(ct.getPatientid());
        
        ct = cdao.getTicket(ct, currentDate);
        ct.setName(p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname());
        ct.setConsultedbyname(loggedInUser.getFullname());
        
        
        medicines = pmdao.getPrescribedToday(ct.getPatientid(), currentDate);
        tickets = cdao.getTodaySchedule(loggedInUser, currentDate);
        
        
        
        if(ct.getConsultstatus().equals("C"))
            request.setAttribute("c", "complete");
        request.setAttribute("medicines", medicines);
        request.setAttribute("currentticket", ct);
        request.setAttribute("schedule", tickets);
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/viewcurrentschedule.jsp");
        rd.forward(request, response);
    }

}
