package Modelo;

public class Moneda implements Comparable<Moneda>{
    private String tipo; //fiat o crypto deberia ser enum
	private String nombre;//bitcoin
    private String nomenclatura;//BTC
    private double valorDolar; //esto no lo teniamos
    private double volatilidad;//0 a 100 ver como ponerlo asi
    private double stock;   
    
    public Moneda() {
    	
    }
    
    public Moneda(String tipo, String nombre, String nomenclatura, double valorDolar, double volatilidad, double stock) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.valorDolar = valorDolar;
		this.volatilidad = volatilidad;
		this.stock = stock;
	}
	
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNomenclatura() {
		return nomenclatura;
	}
	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getValorDolar() {
		return valorDolar;
	}
	public void setValorDolar(double valorDolar) {
		this.valorDolar = valorDolar;
	}
	public double getVolatilidad() {
		return volatilidad;
	}
	public void setVolatilidad(double volatilidad) {
		this.volatilidad = volatilidad;
	}
	
	public double getStock() {
		return stock;
	}
	
	public void setStock(double stock) {
		this.stock=stock;
	}
	
	@Override  
	public int compareTo(Moneda otraMoneda) { 
		return Double.compare(this.valorDolar, otraMoneda.valorDolar); 
	}
	
	@Override
	public String toString() {
	    return "Tipo: " + tipo +
	           ", Nombre: " + nombre +
	           ", Nomenclatura: " + nomenclatura +
	           ", Valor en Dólares: " + valorDolar;
	}
	
}