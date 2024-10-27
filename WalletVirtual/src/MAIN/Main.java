package MAIN;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
//Hay forma de importar todo esto sin hacer tanto quilombo? es con los packages creo pero no se como se hace xd
import DAO.ConexionBD;														
import DAO.MonedaDAOImpl;
import Interfaces.MonedaDAO;
import Modelo.Moneda;
import DAO.ActivoDAOImpl;
import Interfaces.ActivoDAO;
import Modelo.Activo;
import DAO.TransaccionDAOImpl;
import Interfaces.TransaccionDAO;
import Modelo.Transaccion;
import DAO.StockDAOImpl;
import Interfaces.StockDAO;
import Modelo.Stock;

public class Main {
	public static void main(String [] args) {
		try (Connection connection = ConexionBD.getConnection();                      //Esto esta para que se elimine la tabla asi se cargan los datos en cada prueba
		         Statement stmt = connection.createStatement()) {					  // El conexion realizada aparece dos veces por culpa de este try and catch.
		        stmt.executeUpdate("DELETE FROM MONEDA");                             //Elimina los datos de moneda
		        stmt.executeUpdate("DELETE FROM ACTIVO");							  //Elimina los datos de activo
		        stmt.executeUpdate("DELETE FROM TRANSACCION");						//Elimina los datos de transaccion
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	
	//PARA MONEDAS
	 System.out.println("----------------------------------------------");
     System.out.println("MONEDAS");	
	 System.out.println("----------------------------------------------");
   	 MonedaDAO mon = new MonedaDAOImpl();        //hice que primero sea MonedaDAO y despues MonedaDAOImpl porq es lo q hacia el mitocode
     // Crear monedas
     mon.crear(new Moneda("C", "Dogecoin", "DOGE", 3000.00, 99.00));        //crear() es el que instancia la BD x 1ra vez.
     mon.crear(new Moneda("F", "Pesos Argentinos", "ARS", 1000.00, 50.00));
     mon.crear(new Moneda("C", "Bitcoin", "BTC", 44000.00, 15.00));
     //Listar las 3 creadas
     List<Moneda> monedas = mon.listar();
     for (Moneda moneda : monedas) {
         System.out.println(moneda.getNomenclatura() + " - " + moneda.getNombre() + " - " + moneda.getValorDolar());
     }
     //Actualizo BTC y le cambio el valor,  funciona por nomenclatura
     mon.actualizar(new Moneda("C", "Bitcoin", "BTC", 3000.00, 20.00));
    //Listo de vuelta las monedas para efectuar el cambio
     List<Moneda> monedas2 = mon.listar();
     for (Moneda moneda : monedas2) {
    	 System.out.println(moneda.getNomenclatura() + " - " + moneda.getNombre() + " - " + moneda.getValorDolar());
     }
     //PARA ACTIVOS  
     System.out.println("----------------------------------------------");
     System.out.println("ACTIVOS");
 	 System.out.println("----------------------------------------------");
     ActivoDAO act = new ActivoDAOImpl(); 
     // Crear activos
     act.crear(new Activo("DOGE", 100.00));
     act.crear(new Activo("BTC", 150.00));
     //Listo los activos
     List<Activo> activos = act.listar();
     for (Activo activo : activos) {
         System.out.println(activo.getNomenclatura() + " - " + activo.getCantidad());
     }
     //Actualizo un activo, DOGE en este caso
     act.actualizar(new Activo("DOGE", 200.00));
     //Listar de vuelta para ver si se actualizo el valor
     List<Activo> activos2 = act.listar();
     for (Activo activo : activos2) {
         System.out.println(activo.getNomenclatura() + " - " + activo.getCantidad());
     }
     // PARA TRANSACCIONES
	 System.out.println("----------------------------------------------");
	 System.out.println("TRANSACCIONES");	
	 System.out.println("----------------------------------------------");
     
     TransaccionDAO tran = new TransaccionDAOImpl();
     //Crear transacciones
     tran.crear(new Transaccion("Compra_Exitosa", LocalDateTime.now()));
     tran.crear(new Transaccion("Swap_Exitoso", LocalDateTime.of(2024, 10, 27, 15, 30)));
     //Listo las transacciones
     List<Transaccion> transacciones = tran.listar();												//No pide como tal listar las transacciones pero agregue para usar el metodo.
     for (Transaccion transaccion : transacciones) {
         System.out.println(transaccion.getResumen() + " - " + transaccion.getFechaHora());
     }
     //Actualizo una transaccion
     tran.actualizar(new Transaccion("Compra_Exitosa", LocalDateTime.of(2024, 10, 22, 15, 30)));
     //Listo de vuelta las transacciones para que se note el cambio
     List<Transaccion> transacciones2 = tran.listar();
     for (Transaccion transaccion : transacciones2) {
         System.out.println(transaccion.getResumen() + " - " + transaccion.getFechaHora());
     }
     
     // PARA STOCK
	 System.out.println("----------------------------------------------");
	 System.out.println("STOCK");	
	 System.out.println("----------------------------------------------");
     
	 StockDAO stck = new StockDAOImpl(); 
     // Crear stock
     stck.crear(new Stock("ETH", 500.00));
     stck.crear(new Stock("BTC", 200.00));
     //Listo el stock
     List<Stock> stocks = stck.listar();
     for (Stock stock : stocks) {
         System.out.println(stock.getNomenclatura() + " - " + stock.getCantidad());
     }
     //Actualizo un stock, BTC en este caso
     stck.actualizar(new Stock("BTC", 250.00));
     //Listar de vuelta para ver si se actualizo el valor
     List<Stock> stocks2 = stck.listar();
     for (Stock stock : stocks2) {
         System.out.println(stock.getNomenclatura() + " - " + stock.getCantidad());
     }
	 
     
     //cierro la conexion
     ConexionBD.closeConnection();
	}
}
