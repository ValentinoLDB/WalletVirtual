package Modelo;

import java.time.LocalDateTime;

public class Transaccion {
    private String resumen;           // string con la data del resumen
    private LocalDateTime fechaHora;  // FECHA
    // Operacion
    // Estado
// Podriamos ver aca de si agregar los atributos que teniamos en el UML o respetar directamente lo que nos dieron ellos en la tabla
    //En mi opinion no porque este modelo de Transaccion es re distinto al nuestro xdd
    
    public Transaccion() {
    	
    }
    
    public Transaccion(String resumen, LocalDateTime fechaHora) {
    	this.resumen = resumen;
    	this.fechaHora = fechaHora;
    }

    // Getters y setters
    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}
