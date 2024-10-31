
package MAIN;

import DAO.*;
import Modelo.*;
import Interfaces.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonedaDAO monedaDAO = new MonedaDAOImpl();
        ActivoDAO activoDAO = new ActivoDAOImpl();
        TransaccionDAO transaccionDAO = new TransaccionDAOImpl();
        Gestor gestor = new Gestor();
        boolean continuar = true;
        
  /*    try (Connection connection = ConexionBD.getConnection();                      //Esto esta para que se elimine la tabla asi se cargan los datos en cada prueba
		         Statement stmt = connection.createStatement()) {					  // El conexion realizada aparece dos veces por culpa de este try and catch.
		        stmt.executeUpdate("DELETE FROM MONEDA");                             //Elimina los datos de moneda
		        stmt.executeUpdate("DELETE FROM ACTIVO");							  //Elimina los datos de activo
		        stmt.executeUpdate("DELETE FROM TRANSACCION");						//Elimina los datos de transaccion
		    } catch (SQLException e) {
		        e.printStackTrace();		
		    }                          	   */
       System.out.println("Bienvenido al sistema de Billetera Virtual");
        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| 1. Crear Moneda   |  4. Listar Stock        |  7. Simular Compra |");
            System.out.println("| 2. Listar Monedas |  5. Generar Mis Activos |  8. Simular Swap   |");
            System.out.println("| 3. Generar Stock  |  6. Listar Mis Activos  |  9. Salir          |");
            System.out.println("--------------------------------------------------------------------");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1: // Crear Moneda
                    gestor.crearMoneda();
                    break;
                case 2: // Listar Monedas
                    gestor.listarMonedas();
                    break;
                case 3: // Generar Stock 
                    gestor.generarStock();							
                    break;
                case 4: // Listar Stock
                	gestor.listarStock();								
                    break;
                case 5: // Generar Mis Activos
                	gestor.generarActivo();
                    break;
                case 6: // Listar Mis Activos
                    gestor.listarActivos();
                    break;
                case 7: // Simular Compra de Criptomoneda
                    simularCompra(scanner, monedaDAO, activoDAO, transaccionDAO);
                    break;
                case 8: // Simular Swap de Criptomoneda
                    simularSwap(scanner, monedaDAO, activoDAO, transaccionDAO);
                    break;
                case 9: // Salir
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        }
        
        scanner.close();
        ConexionBD.closeConnection(); // Cerrar la conexión al final
        System.out.println("Saliendo del sistema...");
    }

    private static void simularCompra(Scanner scanner, MonedaDAO monedaDAO, ActivoDAO activoDAO, TransaccionDAO transaccionDAO) {
        // Implementar lógica para simular compra de criptomoneda
    }

    private static void simularSwap(Scanner scanner, MonedaDAO monedaDAO, ActivoDAO activoDAO, TransaccionDAO transaccionDAO) {
        // Implementar lógica para simular swap de criptomonedas
    }
}