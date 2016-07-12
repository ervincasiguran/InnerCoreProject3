/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.PatientDAO;
import com.model.Patient;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EditBasicDetails extends BaseServlet {

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
        PatientDAO pdao = new PatientDAO();
        
        p.setPatientid(Integer.parseInt(request.getParameter("patientid")));
        p.setRegionid(request.getParameter("regionid"));
        p.setCityprovinceid(request.getParameter("cityprovinceid"));
        p.setResidencetelno(request.getParameter("residencetelno"));
        p.setCellphonenum(request.getParameter("cellphonenum"));
        p.setCivilstatus(request.getParameter("civilstatus"));
        p.setSpecificaddress(request.getParameter("specificaddress"));
        p.setOccupationaddress(request.getParameter("occupationaddress"));
        p.setOccupationlandline(request.getParameter("occupationtelno"));
        p.setOccupationname(request.getParameter("occupation"));
        
        pdao.editPatientDetails(p);
        
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/ShowPatientDetails?patientid=" + p.getPatientid());
        rd.forward(request, response);
        
    }

}
