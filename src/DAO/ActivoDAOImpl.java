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
    
    @Override
    public Activo obtener(String nomenclatura) {
        String sql = "SELECT * FROM ACTIVO WHERE NOMENCLATURA = ?";
        Connection connection = ConexionBD.getConnection();
        Activo activo = null;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nomenclatura);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    activo = new Activo();
                    activo.setNomenclatura(rs.getString("NOMENCLATURA"));
                    activo.setCantidad(rs.getDouble("CANTIDAD"));
                    // Asigna otros atributos necesarios de la clase Activo, si los hay
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        
        return activo;  // Retorna el activo si se encuentra, o null si no existe
    }
}
