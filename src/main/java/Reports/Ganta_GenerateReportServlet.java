/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author S525701
 */
@WebServlet(name = "Ganta_GenerateReportServlet", urlPatterns = {"/Ganta_GenerateReportServlet"})
public class Ganta_GenerateReportServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, JRException {
        String reportPath = "C:\\Users\\S525718\\Documents\\NetBeansProjects\\JasperMaven\\src\\main\\java\\Reports\\MyFirstReport.jrxml";
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        JasperReport report = JasperCompileManager.compileReport(reportPath);
        byte[] byteStream = JasperRunManager.runReportToPdf(report, null, con);

        OutputStream outStream = response.getOutputStream();
        response.setHeader("Content-Disposition", "inline; filename=\"MyFirstReport.pdf\"");
        response.setContentType("application/pdf");
        response.setContentLength(byteStream.length);
        outStream.write(byteStream, 0, byteStream.length);

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
        } catch (SQLException | JRException ex) {
            Logger.getLogger(Ganta_GenerateReportServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException | JRException ex) {
            Logger.getLogger(Ganta_GenerateReportServlet.class.getName()).log(Level.SEVERE, null, ex);
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
