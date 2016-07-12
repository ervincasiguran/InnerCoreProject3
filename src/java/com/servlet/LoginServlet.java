/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.AdmissionsDAO;
import com.dao.ApplicationContentDAO;
import com.dao.UserDAO;
import com.model.AdmissionRecord;
import com.model.CityRegion;
import com.model.ConsultStatus;
import com.model.ConsultTicket;
import com.model.Medicine;
import com.model.MentalCondition;
import com.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            User oneUser = new User();
            oneUser.setUsername(request.getParameter("username"));
            oneUser.setPassword(request.getParameter("pwd"));
            UserDAO myUsersDB = new UserDAO();
            HashMap<String, String> rights = myUsersDB.strictLogin(oneUser);

            ApplicationContentDAO acdao = new ApplicationContentDAO();
            ArrayList<CityRegion> regioncodes = acdao.getRegionCodes();
            ArrayList<CityRegion> citycodes = acdao.getCityCodes();
            ArrayList<MentalCondition> mentalconditions = acdao.getConditions();
            ArrayList<User> doctorIDs = acdao.getDoctorIDs();
            ArrayList<User> nurseIDs = acdao.getNurseIDs();
            ArrayList<Medicine> medicinelist = acdao.getMedicineList();
            ArrayList<ConsultStatus> consultStatuses = acdao.getConsultStatuses();
                    
            AdmissionsDAO adao = new AdmissionsDAO();
            ArrayList<AdmissionRecord> activepatients = new ArrayList<AdmissionRecord>();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            activepatients = adao.getThisMonthActivePatient(year, month);

            ServletContext application = getServletContext();
            application.setAttribute("regioncodes", regioncodes);
            application.setAttribute("citycodes", citycodes);
            application.setAttribute("mentalconditions", mentalconditions);
            application.setAttribute("nurses", nurseIDs);
            application.setAttribute("doctors", doctorIDs);
            application.setAttribute("activepatients", activepatients);
            application.setAttribute("medicinelist", medicinelist);
            application.setAttribute("consultStatuses", consultStatuses);

            if (rights != null) {
                oneUser = myUsersDB.getUser(oneUser);
                HttpSession session = request.getSession();
                session.setAttribute("rights", rights);
                session.setAttribute("user", oneUser);
                // session.setAttribute("regioncodes", regioncodes);
                // session.setAttribute("citycodes", citycodes);
                // session.setAttribute("mentalconditions", mentalconditions);
                //System.out.println("successful login");
                //ServletContext context = getServletContext();
                RequestDispatcher rd = application.getRequestDispatcher("/OverviewServlet");
                rd.forward(request, response);
            } else {
                //System.out.println("failed login");
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        } finally {
            out.close();
        }
    }

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

}
