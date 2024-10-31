package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Interfaces.ActivoDAO;
import Modelo.Activo;

public class ActivoDAOImpl implements ActivoDAO {

    @Override
    public void crear(Activo activo) {
        String sql = "INSERT INTO ACTIVO (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";
        Connection connection = ConexionBD.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, activo.getNomenclatura());
            pstmt.setDouble(2, activo.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Activo> listar() {
        List<Activo> activos = new ArrayList<>();
        String sql = "SELECT * FROM ACTIVO";
        Connection connection = ConexionBD.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Activo activo = new Activo();
                activo.setNomenclatura(rs.getString("NOMENCLATURA"));
                activo.setCantidad(rs.getDouble("CANTIDAD"));
                activos.add(activo);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return activos;
    }
    
    @Override
    public void actualizar(Activo activo) {
        String sql = "UPDATE ACTIVO SET CANTIDAD=? WHERE NOMENCLATURA=?";
        Connection connection = ConexionBD.getConnection();
        try	(PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, activo.getCantidad());
            pstmt.setString(2, activo.getNomenclatura());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean existeNomenclatura(String nomenclatura) {				//Revisa la existencia de la nomenclatura para generar el activo.
        String sql = "SELECT 1 FROM MONEDA WHERE NOMENCLATURA = ?";
        Connection connection = ConexionBD.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Devuelve true si encuentra un registro
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Devuelve false si ocurre un error o no encuentra la nomenclatura
    }
}
