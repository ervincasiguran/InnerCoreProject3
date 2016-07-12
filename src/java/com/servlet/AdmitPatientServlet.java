/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.AdmissionsDAO;
import com.model.AdmissionRecord;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
public class AdmitPatientServlet extends BaseServlet {


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
        
        AdmissionRecord ar = new AdmissionRecord();
        ar.setPatientid(Integer.parseInt(request.getParameter("patientid")));
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
        
        AdmissionsDAO adao = new AdmissionsDAO();
        adao.admitPatient(ar);
        
        
        RequestDispatcher rd = context.getRequestDispatcher("/ShowPatientDetails?patientid="+ ar.getPatientid());
        rd.forward(request, response);
        
    }

}
