/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.AdmissionsDAO;
import com.model.AdmissionRecord;
import com.model.AdmissionReport;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ReportingServlet extends BaseServlet {

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
        AdmissionsDAO adao = new AdmissionsDAO();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        ArrayList<AdmissionRecord> inpatients = new ArrayList<AdmissionRecord>();
        ArrayList<AdmissionRecord> outpatients = new ArrayList<AdmissionRecord>();
        ArrayList<AdmissionReport> top10illness = new ArrayList<AdmissionReport>();
        ArrayList<AdmissionRecord> dischargedpatients = new ArrayList<AdmissionRecord>();
        inpatients = adao.getThisMonthInpatient(year, month);
        outpatients = adao.getThisMonthOutpatient(year, month);
        top10illness = adao.getTop10Illness();
        dischargedpatients = adao.getDischargedPatientsForThisMonth(year, month);
        
        ServletContext application = getServletContext();
        request.setAttribute("inpatients", inpatients);
        request.setAttribute("outpatients", outpatients);
        request.setAttribute("top10illness", top10illness);
        request.setAttribute("dischargedpatients", dischargedpatients);
        
        RequestDispatcher rd = application.getRequestDispatcher("/reportspage.jsp");
        rd.forward(request, response);
    }

}
