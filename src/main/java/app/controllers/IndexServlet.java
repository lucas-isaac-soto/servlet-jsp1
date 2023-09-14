package app.controllers;

import app.models.CuentaBancaria;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author uniluk
 */

@WebServlet({"/"})
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CuentaBancaria.rellenar();
        LinkedList<CuentaBancaria> cuentasActivas = CuentaBancaria.getCuentasActivas();
        
        req.setAttribute("cuentas", cuentasActivas);
        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
    }
    
}
