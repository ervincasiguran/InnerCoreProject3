/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.ConsultDAO;
import com.dao.SearchPatientDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.model.AdmissionRecord;
import com.model.ConsultTicket;
import com.model.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
public class ConsultServlet extends BaseServlet {

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
        
        
        ServletContext context = getServletContext();
        ConsultTicket ct = new ConsultTicket();
        ConsultDAO cdao = new ConsultDAO();
        SearchPatientDAO sdao = new SearchPatientDAO();
        
        ct.setPatientid(Integer.parseInt(request.getParameter("patientid")));
        ct.setDateofconsultation(request.getParameter("dateofconsultation").trim());
        ct.setComplaints(request.getParameter("complaints").trim());
        ct.setAddedby(Integer.parseInt(request.getParameter("addedBy")));
        ct.setConsultedby(Integer.parseInt(request.getParameter("attendphysician")));
        ct.setDatetimadded(new Timestamp(System.currentTimeMillis()));
        cdao.addTicket2(ct);
        Patient p = new Patient();
        p = sdao.getPatient(ct.getPatientid());
        ct.setName(p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname());
        
        request.setAttribute("currentticket", ct);
        RequestDispatcher rd = context.getRequestDispatcher("/viewschedule.jsp");
        rd.forward(request, response);
    }

}
