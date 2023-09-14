package app.controllers;

import app.models.CuentaBancaria;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author uniluk
 */
@WebServlet({"/transferencia"})
public class TransferenciaServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        CuentaBancaria cuantaBuscada;
        
        cuantaBuscada = CuentaBancaria.buscarCuenta(Integer.valueOf(req.getParameter("cuentaOrigen")));
        req.setAttribute("cuentaOrigen", cuantaBuscada);
        req.setAttribute("origen", Integer.valueOf(req.getParameter("cuentaOrigen")));
        
        cuantaBuscada = CuentaBancaria.buscarCuenta(Integer.valueOf(req.getParameter("cuentaDestino")));
        req.setAttribute("cuentaDestino", cuantaBuscada);
        req.setAttribute("destino", Integer.valueOf(req.getParameter("cuentaDestino")));
        
        req.getRequestDispatcher("/WEB-INF/views/transferencia.jsp").forward(req, resp);
    }
    
}
