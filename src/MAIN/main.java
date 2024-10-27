package MAIN;

import java.util.List;

import DAO.MonedaDAOImpl;
import DAO.ConexionBD;
import Modelo.Moneda;

public class main {

    public static void main(String[] args) {
        MonedaDAOImpl mon = new MonedaDAOImpl();

        // Crear monedas
        mon.crear(new Moneda("C", "Dogecoin", "DOGE", 3000.00, 99.00));
        mon.crear(new Moneda("F", "Pesos Argentinos", "ARS", 1000.00, 50.00));
        
        // Listar monedas
        List<Moneda> monedas = mon.listar();
        for (Moneda moneda : monedas) {
            System.out.println(moneda.getNombre() + " - " + moneda.getValorDolar());
        }

        // Cerrar la conexión
        ConexionBD.closeConnection();
    }
}
