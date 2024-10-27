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
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
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
        try (Connection connection = ConexionBD.getConnection();
             Statement stmt = connection.createStatement();
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
        String sql = "UPDATE TRANSACCION SET FECHA_HORA=? WHERE RESUMEN=?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(transaccion.getFechaHora()));
            pstmt.setString(2, transaccion.getResumen());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}