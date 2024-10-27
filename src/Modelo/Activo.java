package Modelo;

public class Activo {
    private String nomenclatura;  // identificador de la moneda
    private double cantidad;    

    // lo deje sin constructor para q este el vacio y me deje instanciarlo asi nomas

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
}