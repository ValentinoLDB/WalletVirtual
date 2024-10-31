package Modelo;

public class Activo implements Comparable<Activo> {
    private String nomenclatura;  // identificador de la moneda
    private double cantidad;    

    public Activo() {                    //para ActivoDAOImpl
    	
    }
    
    public Activo(String nomenclatura, double cantidad) {         //para el main
    	this.nomenclatura = nomenclatura;
    	this.cantidad = cantidad;
    }
    

    // Getters y setters
    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override 
    public int compareTo(Activo otroActivo) { 
    	return Double.compare(this.cantidad, otroActivo.cantidad); 
    }
}