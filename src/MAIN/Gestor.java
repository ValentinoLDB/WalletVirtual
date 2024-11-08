package MAIN;

import java.time.LocalDateTime;
import java.util.Collections;	
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import DAO.*;
import Modelo.*;
import Comparadores.*;

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
        System.out.println("Ingrese volatilidad:    (Ejemplo: 0,00)");
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
        	if(monedaDAO.obtener(nomenclatura) == null) {
        		monedaDAO.crear(new Moneda(tipo, nombre, nomenclatura, valorDolar, volatilidad, stock));
        		System.out.println("Moneda creada exitosamente.");
        	}
        	else {
        		System.out.println("Ocurrió un error. La moneda ya existe en el sistema.");
        	}
        }
        else {
        	System.out.println("Operacion cancelada.");
        }
    }
	
	public void listarMonedas(boolean ordenarPorNomenclatura) {
	    List<Moneda> monedas = monedaDAO.listar();
	    if (ordenarPorNomenclatura) {
	    	monedas.sort(new MonedaComparatorPorNomenclatura());
	    } else {
	   		Collections.sort(monedas); // Usa compareTo de Moneda
	   	}
	   	for (Moneda moneda : monedas) {
	   		System.out.println(moneda.toString());
	   }
	}

	
	public void generarStock() {
		List<Moneda> monedas = monedaDAO.listar(); // Recupera todas las monedas
        Random random = new Random();
        for (Moneda moneda : monedas) {
        	if(moneda.getTipo().equals("C")) {
        		double stockAleatorio = 10 + (5000 - 10) * random.nextDouble(); // Genera stock aleatorio           
        		moneda.setStock(moneda.getStock() + stockAleatorio); // Asigna el stock aleatorio a la moneda
        		monedaDAO.actualizar(moneda); // Actualiza la moneda en la base de datos
        	}
        }
        System.out.println("Stock generado aleatoriamente");
    }
	
	public void listarStock(boolean ordenarPorNomenclatura) {
	    List<Moneda> monedas = monedaDAO.listar();
	    if (ordenarPorNomenclatura) {
	        monedas.sort(new MonedaComparatorPorNomenclatura());
	    } else {
	        monedas.sort(new MonedaComparatorPorStock());
	    }
	    for (Moneda moneda : monedas) {
	        System.out.println(moneda.getNomenclatura() +" "+ moneda.getNombre() + ": " + moneda.getStock());
	    }
	}

	
	public void generarActivo() {
		System.out.println("Ingrese cantidad:");
		double cantidad = scanner.nextDouble();
		System.out.println("Ingrese nomenclatura: ");
		String nomenclatura = scanner.next().toUpperCase();
		if (monedaDAO.obtener(nomenclatura) == null) {
            System.out.println("Error: La nomenclatura ingresada no existe.");
            return;
        }
		System.out.println("¿Confirma los datos? (S/N)");
        String confirmacion = scanner.next().toUpperCase();
        if (confirmacion.equals("S")) {
        	//me fijo si ya poseo el activo y si no lo tengo lo creo
        	Activo act = activoDAO.obtener(nomenclatura);
        	if(act == null) {
        		
        		//instancio el nuevo activo y lo agrego a la tabla
        		
        		activoDAO.crear(new Activo(nomenclatura, cantidad));
        		System.out.println("Activo creado exitosamente.");
        	}
        	else {
        		act.setCantidad(act.getCantidad() + cantidad);	//actualizo la cantidad del activo
        		activoDAO.actualizar(act);
        		System.out.println("Activo actualizado correctamente.");
        	}
        }
        	
        else {
        	System.out.println("Operacion cancelada.");
        }
	}
	
	public void listarActivos(boolean ordenarPorNomenclatura) {
	    List<Activo> activos = activoDAO.listar();
	    if (ordenarPorNomenclatura) {
	        activos.sort(new ActivoComparatorPorNomenclatura());
	    } else {
	        Collections.sort(activos); // Usa compareTo de Activo
	    }
	    for (Activo activo : activos) {
	        System.out.println(activo.getNomenclatura() + ": " + activo.getCantidad());
	    }
	}
	
	public void simularCompra() {
		System.out.println("||     OPERACIÓN DE COMPRA     ||");
		System.out.println("Ingrese nomenclatura de la criptomoneda a comprar: ");
		String nomenclaturaC = scanner.next().toUpperCase();
		System.out.println("Ingrese nomenclatura de la moneda FIAT: ");
		String nomenclaturaF = scanner.next().toUpperCase();
		System.out.println("Ingrese cantidad a comprar de la moneda FIAT: ");
		double cantidad = scanner.nextDouble();
		//primero pregunto si existe la moneda y la almaceno en la variable cripto
		Moneda cripto = monedaDAO.obtener(nomenclaturaC);
		Moneda fiat = monedaDAO.obtener(nomenclaturaF);
		if(cripto != null && fiat != null) {				//si tanto la cripto como la fiat existen comienzo a ejecutar
			
			//calculo la cantidad de la criptomoneda que voy a necesitar
			
			double equivalente = (fiat.getValorDolar() * cantidad) / cripto.getValorDolar();  //multiplico la cantidad de fiat que tengo por su valor en dolar y divido por el precio de la cripto
			
			Activo actF = activoDAO.obtener(nomenclaturaF);   //obtengo el activo de la fiat a utilizar.
			
			if(actF != null){			//chequeo que el activo exista
			
				if(actF.getCantidad() >= cantidad) {			//si la cantidad del activo fiat es mayor o igual a la cantidad a comprar continuo.
				
					//ahora con el equivalente consulto si el stock de la cripto a comprar supera o es igual a lo que quiero comprar
				
					if(cripto.getStock() >= equivalente) {
				
						//para este momento el stock es suficiente para poder efectuar la compra
						//primero reviso si la criptomoneda ya es un activo para asi variar sus valores
				
						System.out.println("Hay stock suficiente para realizar la compra. Serian "+ equivalente+" "+nomenclaturaC);
						System.out.println("¿Confirma la operación? (S/N)");
						String confirmacion = scanner.next().toUpperCase();
						if (confirmacion.equals("S")) {
		        	
							Activo actC = activoDAO.obtener(nomenclaturaC);
							if( actC == null) {   
					
								//dentro de este if es que el activo no existe por lo tanto lo vamos a crear.
								activoDAO.crear(new Activo(nomenclaturaC, equivalente));             //creo el activo con la nomenclatura indicada y como cantidad el equivalente
								cripto.setStock(cripto.getStock() - equivalente);					//actualizo el stock de la cripto
								monedaDAO.actualizar(cripto);										//actualizo el valor en la tabla
								actF.setCantidad(actF.getCantidad() - cantidad);					//actualizo la cantidad de fiat 
								activoDAO.actualizar(actF);											//actualizo en la tabla de activos
											
							}
							else {			//si el activo existe
								actC.setCantidad(actC.getCantidad() + equivalente);				//Incremendo la cantidad del activo
								activoDAO.actualizar(actC);										//actualizo en la tabla los datos del activo
								cripto.setStock(cripto.getStock() - equivalente);					//actualizo el stock de la cripto
								monedaDAO.actualizar(cripto);										//actualizo el valor en la tabla
								actF.setCantidad(actF.getCantidad() - cantidad);					//actualizo la cantidad de fiat 
								activoDAO.actualizar(actF);											//actualizo en la tabla de activos
							}
							
							String resumen = "Operacion: COMPRA - Moneda origen: "+ nomenclaturaF +" - Moneda destino: "+ nomenclaturaC+" - Cantidad: "+cantidad;
							transaccionDAO.crear(new Transaccion(resumen, LocalDateTime.now()));				//Creo la transaccion en la DB
		        	
							System.out.println("Operación realizada efectivamente. Se han acreditado "+ equivalente+" "+nomenclaturaC+" a su cuenta.");
						}
						else {
							System.out.println("Operación cancelada.");
						}
					}
					else {
						System.out.println("Ocurrió un error. No hay suficiente stock.");
					}
				}
				else {
					System.out.println("Ocurrió un error. La cantidad de FIAT ingresado no es suficiente.");
				}
			}
			else {
				System.out.println("Ocurrió un error. No posee la moneda FIAT indicada.");
			}
			
		}
		else {
			System.out.println("Ocurrió un error. Revise las monedas ingresadas.");
		}	
	}	
	
	public void simularSwap() {
		System.out.println("||     OPERACIÓN DE SWAP     ||");
		System.out.println("Ingrese nomenclatura de la criptomoneda a convertir: ");
		String nomenclaturaOrigen = scanner.next().toUpperCase();
		System.out.println("Ingrese cantidad a convertir: ");
		double cantidad = scanner.nextDouble();
		System.out.println("Ingrese nomenclatura de la criptomoneda esperada: ");
		String nomenclaturaDestino = scanner.next().toUpperCase();
		//consulto si existen ambas monedas
		Moneda criptoOrigen = monedaDAO.obtener(nomenclaturaOrigen);
		Moneda criptoDestino = monedaDAO.obtener(nomenclaturaDestino);
		if(criptoOrigen != null && criptoDestino != null) {				//si ambas monedas existen en la billetera continuo.
			
			//calculo la cantidad de la criptomoneda que voy a necesitar
			
			double equivalente = (criptoOrigen.getValorDolar() * cantidad) / criptoDestino.getValorDolar();		//calculo el equivalente en la criptoDestino
			
			Activo activoOrigen = activoDAO.obtener(nomenclaturaOrigen);		//obtengo los activos a utilizar en la operacion.
			Activo activoDestino = activoDAO.obtener(nomenclaturaDestino);
			
			if(activoOrigen != null && activoDestino != null){		//reviso que ambos activos existan en la cuenta del usuario.
				
				if(activoOrigen.getCantidad() >= cantidad) {        //reviso que el usuario posee la cantidad indicada a convertir del activo
					
					//ahora con el equivalente consulto si el stock de la cripto destino es suficiente para realizar el swap
					
					if(criptoDestino.getStock() >= equivalente) {
				
						//para este momento el stock es suficiente para poder efectuar el swap
				
						System.out.println("Hay stock suficiente para realizar el swap. "+cantidad+" "+nomenclaturaOrigen+" a "+equivalente+" "+nomenclaturaDestino);
						System.out.println("¿Confirma la operación? (S/N)");
						String confirmacion = scanner.next().toUpperCase();
						if (confirmacion.equals("S")) {
							
							//para este momento tengo todo lo requerido para poder efectuar el swap
							
							activoOrigen.setCantidad(activoOrigen.getCantidad() - cantidad);		//actualizo la cantidad del activo origen decrementandole lo que intercambiamos
							activoDAO.actualizar(activoOrigen);										//actualizo la tabla
							activoDestino.setCantidad(activoDestino.getCantidad() + equivalente);    //actualizo la cantidad de del activo destino incrementandola con el equivalente
							activoDAO.actualizar(activoDestino);									//actualizo en la tabla el valor
							
							
							//los activos del usuario ya se actualizaron ahora resta actualizar el stock de la billetera
							
							criptoOrigen.setStock(criptoOrigen.getStock() + cantidad);				//agrego el stock que recupere al hacer el swap
							monedaDAO.actualizar(criptoOrigen);
							criptoDestino.setStock(criptoDestino.getStock() - equivalente);  		//decremento el stock de la moneda que convertí
							monedaDAO.actualizar(criptoDestino);
							
							//valores de moneda y activos actualizados en la DB ahora queda la transaccion
							
							String resumen = "Operacion: SWAP   - Moneda origen: "+ nomenclaturaOrigen +" - Moneda destino: "+ nomenclaturaDestino+" - Cantidad: "+cantidad;
							transaccionDAO.crear(new Transaccion(resumen, LocalDateTime.now()));				//Creo la transaccion en la DB
							
							System.out.println("Operación realizada efectivamente. Se han convertido "+cantidad+" "+nomenclaturaOrigen+" a "+equivalente+" "+nomenclaturaDestino);	
						}
						else {
							System.out.println("Operación cancelada.");
						}
					}
					else {
						System.out.println("Ocurrió un error. No hay suficiente stock de la criptomoneda destino.");
					}
				}
				else {
					System.out.println("Ocurrió un error. No posee suficiente cantidad de la criptomoneda a convertir.");
				}
			}
			else {
				System.out.print("Ocurrió un error. No posee todos los activos a utilizar.");
			}
		}
		else {
			System.out.print("Ocurrió un error. Revise las monedas ingresadas");
		}
	}
}

	