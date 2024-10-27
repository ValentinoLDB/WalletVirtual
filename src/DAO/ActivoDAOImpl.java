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
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
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
        try (Connection connection = ConexionBD.getConnection();
             Statement stmt = connection.createStatement();
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

    //esto cuando se usaria? cuando le pifiaste y queres cambiarlo? porque se supone que el activo lo generas una vez sola.
    @Override
    public void actualizar(Activo activo) {
        String sql = "UPDATE ACTIVO SET CANTIDAD=? WHERE NOMENCLATURA=?";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, activo.getCantidad());
            pstmt.setString(2, activo.getNomenclatura());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}