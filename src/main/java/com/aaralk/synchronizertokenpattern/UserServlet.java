/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaralk.synchronizertokenpattern;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaralk
 */
public class UserServlet extends HttpServlet {
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
        Cookie sessionCookie = Utility.getSessionToken(request.getCookies());
        if(sessionCookie != null) {
            String sessionId = sessionCookie.getValue();
            String token_id = KeyStore.map.get(sessionId);
            
            String p_token = request.getParameter("token");
            
            if(p_token.equals(token_id)) {
                //Token matches, this is a legitimate request
                String p_username = request.getParameter("username");
                String p_password = request.getParameter("password");
                response.setStatus(200);
            }
            
            else {
                //Token is missing or mismatched, bad request?
                response.sendError(500, "Missing or incorrect token");
            }
            
            
            
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
