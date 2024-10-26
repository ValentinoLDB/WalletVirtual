package Modelo;

public class Moneda {
	private String nombre;//bitcoin
    private String nomenclatura;//BTC
    private String tipo; //fiat o crypto deberia ser enum
    private float valorDolar; //esto no lo teniamos
    private float volatilidad;//0 a 100 ver como ponerlo asi
	
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
	public float getValorDolar() {
		return valorDolar;
	}
	public void setValorDolar(float valorDolar) {
		this.valorDolar = valorDolar;
	}
	public float getVolatilidad() {
		return volatilidad;
	}
	public void setVolatilidad(float volatilidad) {
		this.volatilidad = volatilidad;
	}
	
}
