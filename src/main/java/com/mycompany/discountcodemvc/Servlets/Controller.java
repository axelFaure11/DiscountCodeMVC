/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.discountcodemvc.Servlets;

import main.DAODiscount_Code;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.DAODiscount_Code;
import main.DataSourceFactory;
import main.DiscountCode;

/**
 *
 * @author pedago
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String code = request.getParameter("discount_code");
            String rate = request.getParameter("rate");
            String action = request.getParameter("action");
            DAODiscount_Code daodc = new DAODiscount_Code(DataSourceFactory.getDataSource());
            if(code == null){
                request.setAttribute("errorName", "Paramètre code non renseigné");
            }
            else if(rate == null){
                request.setAttribute("errorRate", "Paramètre taux non renseigné");
            }
            else if(code.length()>1){
                request.setAttribute("nameTooLong", "Le nom de code est un caractère");
            }
            if(action.equals("ADD") && code != null && rate != null){
                DiscountCode dc = new DiscountCode(code, Float.parseFloat(rate));
                daodc.addNewDiscountCode(dc);
            }
            if(action.equals("DELETE")){
                daodc.deleteDiscountCode(code);
            }
            request.setAttribute("discount_code_table", daodc.getDiscountCodeList());
            System.out.println(daodc.getDiscountCodeList());
            request.getRequestDispatcher("/affichageTable.jsp").include(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
