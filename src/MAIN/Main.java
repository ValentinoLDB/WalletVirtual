package MAIN;

import DAO.*;
import Modelo.*;
import Interfaces.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonedaDAO monedaDAO = new MonedaDAOImpl();
        ActivoDAO activoDAO = new ActivoDAOImpl();
        TransaccionDAO transaccionDAO = new TransaccionDAOImpl();
        Gestor gestor = new Gestor();
        boolean continuar = true;

        // Eliminar tablas para pruebas (opcional)
        /*
        try (Connection connection = ConexionBD.getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM MONEDA");
            stmt.executeUpdate("DELETE FROM ACTIVO");
            stmt.executeUpdate("DELETE FROM TRANSACCION");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

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
                    listarMonedasConOpcion(scanner, gestor);
                    break;
                case 3: // Generar Stock
                    gestor.generarStock();
                    break;
                case 4: // Listar Stock
                    listarStockConOpcion(scanner, gestor);
                    break;
                case 5: // Generar Mis Activos
                    gestor.generarActivo();
                    break;
                case 6: // Listar Mis Activos
                    listarActivosConOpcion(scanner, gestor);
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

    private static void listarMonedasConOpcion(Scanner scanner, Gestor gestor) {
        System.out.println("Seleccione el criterio de ordenación:");
        System.out.println("1. Ordenar por valor en dólares");
        System.out.println("2. Ordenar por nomenclatura");
        int criterio = scanner.nextInt();
        boolean ordenarPorNomenclatura = (criterio == 2);
        gestor.listarMonedas(ordenarPorNomenclatura);
    }

    private static void listarStockConOpcion(Scanner scanner, Gestor gestor) {
        System.out.println("Seleccione el criterio de ordenación:");
        System.out.println("1. Ordenar por cantidad");
        System.out.println("2. Ordenar por nomenclatura");
        int criterio = scanner.nextInt();
        boolean ordenarPorNomenclatura = (criterio == 2);
        gestor.listarStock(ordenarPorNomenclatura);
    }

    private static void listarActivosConOpcion(Scanner scanner, Gestor gestor) {
        System.out.println("Seleccione el criterio de ordenación:");
        System.out.println("1. Ordenar por cantidad");
        System.out.println("2. Ordenar por nomenclatura");
        int criterio = scanner.nextInt();
        boolean ordenarPorNomenclatura = (criterio == 2);
        gestor.listarActivos(ordenarPorNomenclatura);
    }

    private static void simularCompra(Scanner scanner, MonedaDAO monedaDAO, ActivoDAO activoDAO, TransaccionDAO transaccionDAO) {
        // Implementar lógica para simular compra de criptomoneda
    }

    private static void simularSwap(Scanner scanner, MonedaDAO monedaDAO, ActivoDAO activoDAO, TransaccionDAO transaccionDAO) {
        // Implementar lógica para simular swap de criptomonedas
    }
}
