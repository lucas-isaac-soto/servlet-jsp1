package app.exceptions;

import java.util.LinkedList;

/**
 *
 * @author uniluk
 */
public class CuentaException extends RuntimeException{
    private LinkedList<CuentaException> errores = new LinkedList<>();

    public LinkedList<CuentaException> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<CuentaException> errores) {
        this.errores = errores;
    }
    
    
    public CuentaException(){
        super();
    }
    
    public CuentaException(String error){
        super(error);
    }
}
