package MAIN;

import DAO.MonedaDAOImpl;
import Modelo.Moneda;

public class main {

	public static void main(String[] args) {
		MonedaDAOImpl mon= new MonedaDAOImpl();
		mon.crear(new Moneda("C","Dogecoin","DOGE",3000.00,99.00));
		mon.crear(new Moneda("F","Pesos Argentinos","ARS",1000.00,50.00));
		
        List<Moneda> monedas = mon.listar();
        for (Moneda moneda : monedas) {
            System.out.println(moneda.getNombre() + " - " + moneda.getValorDolar());
        }

	}

}
