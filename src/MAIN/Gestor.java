package MAIN;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import DAO.*;
import Modelo.*;

public class Gestor {
	private ActivoDAOImpl activoDAO;
	private MonedaDAOImpl monedaDAO ;
	private TransaccionDAOImpl transaccionDAO;
	private Scanner scanner;
	
	public Gestor() {
		this.monedaDAO = new MonedaDAOImpl();
		this.activoDAO = new ActivoDAOImpl();
		this.transaccionDAO = new TransaccionDAOImpl();
		this.scanner = new Scanner(System.in);
	}
	
	
	public void crearMoneda() {
		System.out.println("Ingrese el tipo de moneda (C para Cripto o F para FIAT):");
        String tipo = scanner.next().toUpperCase();
        while (!tipo.equals("C") && !tipo.equals("F")) {
            System.out.println("Tipo inválido. Ingrese 'C' para Cripto o 'F' para FIAT:");
            tipo = scanner.next().toUpperCase();
        }
        scanner.nextLine();
        System.out.println("Ingrese el nombre de la moneda:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la nomenclatura de la moneda:");
        String nomenclatura = scanner.next().toUpperCase();
        System.out.println("Ingrese el valor en dólares:");
        double valorDolar = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Ingrese volatilidad:");
        double volatilidad = scanner.nextDouble();
        scanner.nextLine();
        Double stock = 0.00;
        if (tipo.equals("F")) {
        	System.out.println("Ingrese stock (puede dejar en blanco si no aplica):");
        	String inStock = scanner.nextLine();
        	if(!inStock.isEmpty()) {
        		stock = Double.valueOf(inStock);
        	}
        }
        else {
        	System.out.println("Ingrese stock:");
        	stock = scanner.nextDouble();
        	scanner.nextLine();
        }
        // Confirmación del usuario
        System.out.println("¿Confirma los datos? (S/N)");
        String confirmacion = scanner.next().toUpperCase();
        if (confirmacion.equals("S")) {
            monedaDAO.crear(new Moneda(tipo, nombre, nomenclatura, valorDolar, volatilidad, stock));
            System.out.println("Moneda creada exitosamente.");
        }
        else {
        	System.out.println("Operacion cancelada.");
        }
    }
	
	  public void listarMonedas( ) {
	        List<Moneda> monedas = monedaDAO.listar();
	        monedas.sort((m1, m2) -> Double.compare(m2.getValorDolar(), m1.getValorDolar())); // Ordenar por valor en dólares
	        System.out.println("Listado de Monedas (ordenadas por valor en dólares):");
	        for (Moneda moneda : monedas) {
	            System.out.println(moneda.getNomenclatura() + " - " + moneda.getNombre() + " - $" + moneda.getValorDolar());
	        }
	    }
	
	public void generarStock() {
		List<Moneda> monedas = monedaDAO.listar(); // Recupera todas las monedas
        Random random = new Random();
        for (Moneda moneda : monedas) {
        	if(moneda.getTipo().equals("C")) {
        		double stockAleatorio = 10 + (500 - 10) * random.nextDouble(); // Genera stock aleatorio
        		moneda.setStock(stockAleatorio); // Asigna el stock aleatorio a la moneda
        		monedaDAO.actualizar(moneda); // Actualiza la moneda en la base de datos
        	}
        }
        System.out.println("Stock generado aleatoriamente");
    }
	
	public void listarStock() {
		List<Moneda> monedas = monedaDAO.listar();
		monedas.sort((m1, m2) -> Double.compare(m2.getStock(), m1.getStock())); // Ordenar por stock
		System.out.println("Listado de Stock:");
		for (Moneda moneda : monedas) {
			System.out.println(moneda.getNomenclatura() + " - Stock: " + moneda.getStock());
		}
	}	
	
	public void generarActivo() {
		System.out.println("Ingrese cantidad:");
		double cantidad = scanner.nextDouble();
		System.out.println("Ingrese nomenclatura: ");
		String nomenclatura = scanner.next().toUpperCase();
		
		if (!activoDAO.existeNomenclatura(nomenclatura)) {
            System.out.println("Error: La nomenclatura ingresada no existe.");
            return;
        }
		System.out.println("¿Confirma los datos? (S/N)");
        String confirmacion = scanner.next().toUpperCase();
        if (confirmacion.equals("S")) {
        	activoDAO.crear(new Activo(nomenclatura, cantidad));
        	System.out.println("Activo creado exitosamente.");
        }
        else {
        	System.out.println("Operacion cancelada.");
        }
	}
	
	public void listarActivos( ) {
        List<Activo> activos = activoDAO.listar();
        activos.sort((a1, a2) -> Double.compare(a2.getCantidad(), a1.getCantidad())); // Ordenar por cantidad
        System.out.println("Listado de Activos:");
        for (Activo activo : activos) {
            System.out.println(activo.getNomenclatura() + " - Cantidad: " + activo.getCantidad());
        }
    }
}

	
	