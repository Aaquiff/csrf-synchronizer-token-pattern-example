/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aaralk.synchronizertokenpattern;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieStore;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaralk
 */
public class TokenServlet extends HttpServlet {

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

        Cookie[] cookies = request.getCookies();
        Cookie sessionCookie = Utility.getSessionToken(cookies);
        if (sessionCookie != null) {
            String tokenId = KeyStore.map.get(sessionCookie.getValue());
            if (tokenId != null && !"".equals(tokenId)) {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.write(String.format("{\"token\":\"%s\"}", tokenId));
            } else {
                response.sendError(400, "Token Not Found for session id: " + sessionCookie.getValue());
            }
        }
        else {
           response.sendError(500, "Session cookie not found"); 
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
