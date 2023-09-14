package app.models;

import app.exceptions.CuentaException;
import app.exceptions.CuentaNoExisteException;
import app.exceptions.CuentasNoValidasException;
import app.exceptions.SaldoException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author uniluk
 */
public class CuentaBancaria implements Serializable {

    public CuentaBancaria() {
    }

    private static LinkedList<CuentaBancaria> cuentasActivas = new LinkedList<CuentaBancaria>();
    private String alias;
    private Integer numeroCuenta;
    private Double saldo;
    private Double descubierto;

    public static LinkedList<CuentaBancaria> getCuentasActivas() {
        return cuentasActivas;
    }

    public Double getDescubierto() {
        return descubierto;
    }

    public void setDescubierto(Double descubierto) {
        this.descubierto = descubierto;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String toString() {
        return alias + " - " + numeroCuenta + " - " + saldo + " - " + descubierto;
    }

    //DEBE LANZAR UNA EXCEPCION
    public static CuentaBancaria buscarCuenta(Integer codigoCuenta) throws CuentaNoExisteException {
        for (CuentaBancaria cuentaBuscada : CuentaBancaria.cuentasActivas) {
            if (cuentaBuscada.getNumeroCuenta() == codigoCuenta) {
                return cuentaBuscada;
            }
        }
        throw new CuentaNoExisteException("La cuenta numero " + codigoCuenta);
    }

    public static void rellenar() {
        if (CuentaBancaria.getCuentasActivas().isEmpty()) {
            String alias[] = {"reyna-de-corazones", "rey-de-trebol", "caballero-de-copas"};
            Double saldosIniciales[] = {1000000.0, 2000000.0, 1500000.0};
            Integer contadorCuenta = 1;
            Double porcentajeDescubierto = 10.0;
            Integer cuentasACrear = 3;

            CuentaBancaria CuentaAuxiliar;

            for (int i = 0; i < cuentasACrear; i++) {
                CuentaAuxiliar = new CuentaBancaria();

                CuentaAuxiliar.setAlias(alias[i]);
                CuentaAuxiliar.setNumeroCuenta(contadorCuenta++);
                CuentaAuxiliar.setSaldo(saldosIniciales[i]);
                CuentaAuxiliar.setDescubierto(saldosIniciales[i] * (porcentajeDescubierto / 100));

                CuentaBancaria.getCuentasActivas().add(CuentaAuxiliar);
            }
        }
    }

    /*NO OLVIDAR TRATAR LA EXCEPCION*/
    public static void validarCuentas(Map<String, String[]> mapaParametros) throws RuntimeException {
        CuentaException errores = new CuentaException();
        CuentaBancaria cuentaOrigen = null;
        CuentaBancaria cuentaDestino = null;
        
        //SI EL VALOR NO ES UN NUMERO VA A DAR UNA EXCEPTION
        Integer numeroCuentaOrigen = Integer.valueOf(mapaParametros.get("cuentaOrigen")[0]);
        Integer numeroCuentaDestino = Integer.valueOf(mapaParametros.get("cuentaDestino")[0]);
        
        //SI EL NUMERO ESTA BIEN PERO LA CUENTA NO EXISTE DA EXCEPTION
        try {
            cuentaOrigen = CuentaBancaria.buscarCuenta(numeroCuentaOrigen);
        } catch (CuentaException errorCuentaOrigen) {
            errores.getErrores().add(errorCuentaOrigen);
        }

        try {
            cuentaDestino = CuentaBancaria.buscarCuenta(numeroCuentaDestino);
        } catch (CuentaException errorCuentaDestino) {
            errores.getErrores().add(errorCuentaDestino);
        }
        
        if(!errores.getErrores().isEmpty())
            throw errores;
        
        
        if (cuentaOrigen == cuentaDestino)
            throw new CuentasNoValidasException("Cuentas Origen y Destino son las mismas no se puede realizar una transferencia entre 2 cuentas iguales");

    }

    public static void transferir(Map<String, String[]> mapaParametros) throws SaldoException{
        //PODRIA VERIFICAR CON UNA EXPRESION REGULAR QUE EL MONTO SEA NNNNN.NN MAX 2 DECIMALES
        
        CuentaBancaria origen = CuentaBancaria.buscarCuenta(Integer.valueOf(mapaParametros.get("cuentaOrigen")[0]) );
        CuentaBancaria destino = CuentaBancaria.buscarCuenta(Integer.valueOf(mapaParametros.get("cuentaDestino")[0]));
        
        //VERIFICAR QUE TENGA SALDO SUFICIENTE LA CUENTA ORIGEN Y EL SALDO NO SEA NEGATIVO
        Double montoTransferencia = Double.valueOf(mapaParametros.get("montoTransferencia")[0]);
        
        if(montoTransferencia <= 0)
            throw new SaldoException("El monto que usted ingreso " + montoTransferencia +" es invalido debe ser mayor o igual a 1");
        
        Double saldoFinalOrigen = origen.getSaldo() - montoTransferencia;
        
        if(saldoFinalOrigen < origen.getDescubierto())
            throw new SaldoException("El monto que usted ingreso supera el descubierto de su cuenta (-$" + origen.getDescubierto() +")");
        
        origen.setSaldo(saldoFinalOrigen);
        destino.setSaldo(destino.getSaldo() + montoTransferencia);
        
    }

}
