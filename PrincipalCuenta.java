import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalCuenta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Cuenta> cuentas = new ArrayList<>();
        int actual = -1;
        
        System.out.println("======================================");
        System.out.println("   CLI de Prueba - Clase Cuenta");
        System.out.println("======================================");
        
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú principal");
            System.out.println("1) Crear Cuenta");
            System.out.println("2) Conocer la cantidad de Cuentas Creadas");
            System.out.println("3) Listar Cuentas");
            System.out.println("4) Seleccionar Cuenta actual");
            System.out.println("5) Depositar");
            System.out.println("6) Retirar");
            System.out.println("7) Consultar Saldo");
            System.out.println("8) Consultar Estado (toString)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            
            switch (op) {
                case "1": {
                    System.out.print("Nombre del cuenta habiente (enter para asignar después): ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Saldo inicial: ");
                    String linea = sc.nextLine().trim();
                    Cuenta c;
                    double saldo;
                    try {
                        saldo = Double.parseDouble(linea);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido, se usará 0.0");
                        saldo = 0.0;
                    }
                    
                    if (nombre.isEmpty()) {
                        c = new Cuenta(saldo);
                        System.out.print("¿Desea asignar nombre ahora? (s/n): ");
                        String resp = sc.nextLine().trim();
                        if (resp.toLowerCase().equals("s")) {
                            System.out.print("Nombre del cuenta habiente: ");
                            String nombreNuevo = sc.nextLine().trim();
                            c.setNombreCuentaHabiente(nombreNuevo);
                        }
                    } else {
                        c = new Cuenta(nombre, saldo);
                    }
                    
                    cuentas.add(c);
                    actual = cuentas.size() - 1;
                    System.out.println("Cuenta creada y seleccionada (índice " + actual + ").");
                    break;
                }
                case "2": {
                    System.out.println("Total de cuentas creadas: " + Cuenta.getCantCuentasCreadas());
                    break;
                }
                case "3": {
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas creadas.");
                    } else {
                        System.out.println("Índice | Código | Cuenta Habiente | Saldo");
                        for (int i = 0; i < cuentas.size(); i++) {
                            Cuenta c = cuentas.get(i);
                            System.out.printf("  %d    | %s | %s | %.2f%n",
                                    i,
                                    c.getCodCuenta(),
                                    c.toString().contains("Cuenta Habiente: \n") ? "(sin nombre)" : 
                                    c.toString().split("Cuenta Habiente: ")[1].split("\n")[0],
                                    c.getSaldo());
                        }
                    }
                    break;
                }
                case "4": {
                    if (cuentas.isEmpty()) {
                        System.out.println("Cree una cuenta primero.");
                        break;
                    }
                    System.out.print("Índice de la cuenta a seleccionar: ");
                    String idxS = sc.nextLine().trim();
                    try {
                        int idx = Integer.parseInt(idxS);
                        if (idx >= 0 && idx < cuentas.size()) {
                            actual = idx;
                            System.out.println("Cuenta índice " + actual + " seleccionada.");
                        } else {
                            System.out.println("Índice fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Índice inválido.");
                    }
                    break;
                }
                case "5": {
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a depositar: ");
                    String s = sc.nextLine().trim();
                    double monto;
                    try {
                        monto = Double.parseDouble(s);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido, no se realizó depósito.");
                        break;
                    }
                    
                    double saldoAnterior = cuentas.get(actual).getSaldo();
                    double nuevoSaldo = cuentas.get(actual).depositar(monto);
                    
                    if (nuevoSaldo > saldoAnterior) {
                        System.out.println("Depósito realizado exitosamente.");
                    } else {
                        System.out.println("No se pudo realizar el depósito (monto debe ser positivo).");
                    }
                    System.out.printf("Saldo actual: %.2f%n", nuevoSaldo);
                    break;
                }
                case "6": {
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.print("Monto a retirar: ");
                    String s = sc.nextLine().trim();
                    double monto;
                    try {
                        monto = Double.parseDouble(s);
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido, no se realizó retiro.");
                        break;
                    }
                    
                    double saldoAnterior = cuentas.get(actual).getSaldo();
                    double nuevoSaldo = cuentas.get(actual).retirar(monto);
                    
                    if (nuevoSaldo < saldoAnterior) {
                        System.out.println("Retiro realizado exitosamente.");
                    } else {
                        System.out.println("No se pudo realizar el retiro (fondos insuficientes o monto inválido).");
                    }
                    System.out.printf("Saldo actual: %.2f%n", nuevoSaldo);
                    break;
                }
                case "7": {
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.printf("Saldo actual: %.2f%n", cuentas.get(actual).getSaldo());
                    break;
                }
                case "8": {
                    if (actual < 0 || cuentas.isEmpty()) {
                        System.out.println("Debe crear y seleccionar una cuenta primero.");
                        break;
                    }
                    System.out.println(cuentas.get(actual).toString());
                    break;
                }
                case "0": {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                }
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }
}