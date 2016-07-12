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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DischargePatient extends BaseServlet {

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
        try {
            String patientid = request.getParameter("patientid");
            String datetimeadmitted = request.getParameter("datetimeadmitted");
            String dischargedby = request.getParameter("dischargedby");
            String dischargedate = request.getParameter("dischargedate");
            String hourdischarge = request.getParameter("hourtime");
            String minutedischarge = request.getParameter("minutetime");
            String finaldiagnosis = request.getParameter("finaldiagnosis");
            String recommendation = request.getParameter("recommendation");
            String reasonfordischargeid = request.getParameter("finalresult");
            
            dischargedate += " "+ hourdischarge + ":" + minutedischarge+":00.0";
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date dta = sdf.parse(datetimeadmitted);
            Timestamp t = new Timestamp(dta.getTime());
            
            AdmissionRecord ar = new AdmissionRecord();
            ar.setPatientid(Integer.parseInt(patientid));
            ar.setDischargedby(dischargedby);
            ar.setDatetimeadmitted(t);
            
            dta = sdf.parse(dischargedate);
            t=new Timestamp(dta.getTime());
            ar.setDischargedate(t);
            
            ar.setFinaldiagnosis(finaldiagnosis);
            ar.setRecommendation(recommendation);
            ar.setReasonfordischarge(reasonfordischargeid);
            
            AdmissionsDAO adao = new AdmissionsDAO();
            
            adao.dischargePatient(ar);
            
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/ShowPatientDetails?patientid="+patientid);
            rd.forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(DischargePatient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
