package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Interfaces.StockDAO;
import Modelo.Stock;

public class StockDAOImpl implements StockDAO {

    @Override
    public void crear(Stock stock) {
        String sql = "INSERT INTO STOCK (NOMENCLATURA, CANTIDAD) VALUES (?, ?)";
        Connection connection = ConexionBD.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, stock.getNomenclatura());
            pstmt.setDouble(2, stock.getCantidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Stock> listar() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM STOCK";
        Connection connection = ConexionBD.getConnection();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setNomenclatura(rs.getString("NOMENCLATURA"));
                stock.setCantidad(rs.getDouble("CANTIDAD"));
                stocks.add(stock);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
        return stocks;
    }

    @Override
    public void actualizar(Stock stock) {
        String sql = "UPDATE STOCK SET CANTIDAD=? WHERE NOMENCLATURA=?";
        Connection connection = ConexionBD.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, stock.getCantidad());
            pstmt.setString(2, stock.getNomenclatura());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}