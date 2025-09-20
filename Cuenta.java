import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cuenta {
    private String codCuenta;
    private double saldo;
    private String nombreCuentaHabiente;
    private String fechaCreacion;
    private int cantDepositosRealizados;
    private int cantRetirosExitososRealizados;
    
    private static int cantCuentasCreadas = 0;
    
    public Cuenta(double pSaldo) {
        this("", pSaldo);
    }
    
    public Cuenta(String nombreCuentaHabienteP, double pSaldo) {
        this.nombreCuentaHabiente = nombreCuentaHabienteP;
        this.saldo = pSaldo;
        
        cantCuentasCreadas++;
        this.codCuenta = "cta-" + System.currentTimeMillis() % 100000;
        this.fechaCreacion = determinarFechaHoraActual();
        
        cantDepositosRealizados = 0;
        cantRetirosExitososRealizados = 0;
    }
    
    public void setNombreCuentaHabiente(String pNombreCuentaHabiente) {
        this.nombreCuentaHabiente = pNombreCuentaHabiente;
    }
    
    public String getCodCuenta() {
        return codCuenta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public static int getCantCuentasCreadas() {
        return cantCuentasCreadas;
    }
    
    public double depositar(double monto) {
        if (validarMonto(monto)) {
            saldo += monto;
            cantDepositosRealizados++;
        }
        return saldo;
    }
    
    public double retirar(double monto) {
        if (validarRetiro(monto)) {
            saldo -= monto;
            cantRetirosExitososRealizados++;
        }
        return saldo;
    }
    
    private boolean validarMonto(double monto) {
        return monto > 0;
    }
    
    private boolean validarRetiro(double monto) {
        return validarMonto(monto) && monto <= saldo;
    }
    
    private String determinarFechaHoraActual() {
        Date fecha = new Date(System.currentTimeMillis());
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return formatoFecha.format(fecha);
    }
    
    public String toString() {
        String msg = "";
        
        msg += "========== Ficha de la Cuenta ==========\n";
        msg += "Código: " + codCuenta + "\n";
        msg += "Cuenta Habiente: " + nombreCuentaHabiente + "\n";
        msg += "Saldo: " + String.format("%.2f", saldo) + "\n";
        msg += "Fecha de Creación: " + fechaCreacion + "\n";
        msg += "Depósitos Realizados: " + cantDepositosRealizados + "\n";
        msg += "Retiros Exitosos Realizados: " + cantRetirosExitososRealizados + "\n";
        msg += "=======================================\n";
        
        return msg;
    }
}