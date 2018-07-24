/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaralk.synchronizertokenpattern;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.rmi.runtime.Log;

/**
 *
 * @author aaralk
 */
public class User extends HttpServlet {
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
            //Get the session id from the session cookie
            String sessionId = sessionCookie.getValue();
            
            //Do we have a token for the session id in the hash map
            String token_id = KeyStore.map.get(sessionId);
            //What was the token in the request
            String p_token = request.getParameter("token");
            //Do the stored token and token in the request match?
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
        else {
            //Session cookit is missing
            response.sendError(500, "Missing session cookie");
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
