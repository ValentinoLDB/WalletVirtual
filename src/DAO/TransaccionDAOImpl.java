package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Interfaces.TransaccionDAO;
import Modelo.Transaccion;

public class TransaccionDAOImpl implements TransaccionDAO {

    @Override
    public void crear(Transaccion transaccion) {
        String sql = "INSERT INTO TRANSACCION (RESUMEN, FECHA_HORA) VALUES (?, ?)";
        Connection connection = ConexionBD.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, transaccion.getResumen());
            pstmt.setTimestamp(2, Timestamp.valueOf(transaccion.getFechaHora()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaccion> listar() {
        List<Transaccion> transacciones = new ArrayList<>();
        String sql = "SELECT * FROM TRANSACCION";
        Connection connection = ConexionBD.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setResumen(rs.getString("RESUMEN"));
                transaccion.setFechaHora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return transacciones;
    }

    @Override
    public void actualizar(Transaccion transaccion) {
        String sql = "UPDATE TRANSACCION SET FECHA_HORA=? WHERE RESUMEN=?";                      //Funciona sinm problema pero tiene sentido que el valor identificativo sea el resumen? 
        Connection connection = ConexionBD.getConnection();										// No es primary key asique no se podria repetir el resumen y que haya dos transacciones con mismo resumen?
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(transaccion.getFechaHora()));
            pstmt.setString(2, transaccion.getResumen());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Override
    public Transaccion obtener(String resumen) {
        String sql = "SELECT * FROM TRANSACCION WHERE RESUMEN = ?";
        Connection connection = ConexionBD.getConnection();
        Transaccion transaccion = null;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, resumen);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    transaccion = new Transaccion();
                    transaccion.setResumen(rs.getString("RESUMEN"));
                    transaccion.setFechaHora(rs.getTimestamp("FECHA_HORA").toLocalDateTime());
                    // Otros atributos si existen
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        return transaccion;  // Devuelve el objeto Transaccion o null si no existe
    }
}