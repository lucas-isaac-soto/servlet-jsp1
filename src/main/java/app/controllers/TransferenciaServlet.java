package app.controllers;

import app.exceptions.CuentaException;
import app.exceptions.SaldoException;
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
public class TransferenciaServlet extends HttpServlet {

    /* Cuando quieran transferir debo:
        que existan las 2 cuentas
        asegurarme que las 2 cuentas son distintas, 
        ademas tengan saldo suficiente para realizar la transferencia
        teniendo en cuenta el porcentaje de descubierto
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //PUEDE DAR 3 EXCEPTION DISTINTAS
        try {
            CuentaBancaria.validarCuentas(req.getParameterMap());
        } catch (CuentaException e) {

        } catch (NumberFormatException e) {

        }

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

        }

    }

}
