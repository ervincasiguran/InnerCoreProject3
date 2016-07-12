/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.MedicalHistoryDAO;
import com.model.MedicalHistory;
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
public class EditMedicalHistory extends BaseServlet {

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
        MedicalHistory mh = new MedicalHistory();
        MedicalHistoryDAO mhdao = new MedicalHistoryDAO();

        String presentingproblems = request.getParameter("presentingproblems");
        String historyofpresentillness = request.getParameter("historyofpresentillness");
        String pastmedicalhistory = request.getParameter("pastmedicalhistory");
        String pastpersonalhistory = request.getParameter("pastpersonalhistory");
        String familyhistory = request.getParameter("familyhistory");

        mh.setPatientid(Integer.parseInt(request.getParameter("patientid")));
        mh.setEditedby(request.getParameter("editedby"));
        mh.setPresentingproblems(presentingproblems);
        mh.setHistoryofpresentillness(historyofpresentillness);
        mh.setPastmedicalhistory(pastmedicalhistory);
        mh.setPastpersonalhistory(pastpersonalhistory);
        mh.setFamilyhistory(familyhistory);
        
        mh.setDatelastedited(new Timestamp(System.currentTimeMillis()));
        
        if (mhdao.checkPatientHistory(mh)) {
            if (!presentingproblems.isEmpty()) {
                mhdao.editPresentingProblems(mh);
            }
            if (!historyofpresentillness.isEmpty()) {
                mhdao.editFamilyHistory(mh);
            }
            if (!pastmedicalhistory.isEmpty()) {
                mhdao.editPastMedicalHistory(mh);
            }
            if (!pastpersonalhistory.isEmpty()) {
                mhdao.editPastPersonalHistory(mh);
            }
            if (!familyhistory.isEmpty()) {
                mhdao.editFamilyHistory(mh);
            }
        } else {
            mhdao.addMedicalHistory(mh);
        }
        
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/ShowPatientDetails?patientid=" + mh.getPatientid());
        rd.forward(request, response);
    }

}
