package app.models;

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

    public static CuentaBancaria buscarCuenta(Integer codigoCuenta) {
        for (CuentaBancaria cuentaBuscada : CuentaBancaria.cuentasActivas) {
            if (cuentaBuscada.getNumeroCuenta() == codigoCuenta) {
                return cuentaBuscada;
            }
        }
        //ERROR CUENTA NO EXISTE
        return null;
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
    public static boolean validarTransferencia( Map<String,String> mapaParametros) {
        
        Integer numeroCuentaOrigen = Integer.valueOf(mapaParametros.get("origen"));
        Integer numeroCuentaDestino = Integer.valueOf(mapaParametros.get("destino"));
        Double montoTransferencia = Double.valueOf(mapaParametros.get("saldoTransferencia"));
        
        
        CuentaBancaria cuentaOrigen = CuentaBancaria.buscarCuenta(numeroCuentaOrigen);
        CuentaBancaria cuentaDestino = CuentaBancaria.buscarCuenta(numeroCuentaDestino);
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void transferir() {
    }

}
