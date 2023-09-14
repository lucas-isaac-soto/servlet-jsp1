package app.controllers;

import app.exceptions.CuentaException;
import app.exceptions.CuentasNoValidasException;
import app.exceptions.SaldoException;
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
@WebServlet({"/transferencia"})
public class TransferenciaServlet extends HttpServlet {

    /* Cuando quieran transferir debo:
        que existan las 2 cuentas
        asegurarme que las 2 cuentas son distintas, 
        ademas tengan saldo suficiente para realizar la transferencia
        teniendo en cuenta el porcentaje de descubierto
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedList<Exception> errores = new LinkedList<>();
        
        //PUEDE DAR 3 EXCEPTION DISTINTAS
        try {
            CuentaBancaria.validarCuentas(req.getParameterMap());
        }catch (CuentaException e) {
            
            for(CuentaException error : e.getErrores())
                errores.add(error);
            
            req.setAttribute("errorCuentas", errores);
            
        } catch (NumberFormatException e) {
            req.setAttribute("errorFormato", e);
        }
        
        if(!errores.isEmpty())
            req.getRequestDispatcher("/WEB-INF/views/system/error.jsp").forward(req, resp);
        
        try {
            CuentaBancaria.transferir(req.getParameterMap());

            CuentaBancaria cuantaBuscada;

            cuantaBuscada = CuentaBancaria.buscarCuenta(Integer.valueOf(req.getParameter("cuentaOrigen")));
            req.setAttribute("cuentaOrigen", cuantaBuscada);

            cuantaBuscada = CuentaBancaria.buscarCuenta(Integer.valueOf(req.getParameter("cuentaDestino")));
            req.setAttribute("cuentaDestino", cuantaBuscada);

            req.setAttribute("montoTransferencia", Double.valueOf(req.getParameter("montoTransferencia")));
            req.getRequestDispatcher("/WEB-INF/views/transferencia.jsp").forward(req, resp);
            
        } catch (SaldoException e) {
            req.setAttribute("errorSaldo", e);
            req.getRequestDispatcher("/WEB-INF/views/system/error.jsp").forward(req, resp);
        }

    }

}
