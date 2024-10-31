package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import Interfaces.MonedaDAO;
import Modelo.Moneda;

public class MonedaDAOImpl implements MonedaDAO {

	@Override
	public void crear(Moneda moneda) {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD, STOCK) VALUES (?, ?, ?, ?, ?, ?)";
      	Connection connection = ConexionBD.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, moneda.getTipo());
            pstmt.setString(2, moneda.getNombre());
            pstmt.setString(3, moneda.getNomenclatura());
            pstmt.setDouble(4, moneda.getValorDolar());
            pstmt.setDouble(5, moneda.getVolatilidad());
            pstmt.setDouble(6, moneda.getStock());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
        }
	}

	@Override
	public List<Moneda> listar() {
		List<Moneda> monedas = new ArrayList<>();
        String sql = "SELECT * FROM MONEDA";
        Connection connection = ConexionBD.getConnection();
        try  (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Moneda moneda = new Moneda();
                moneda.setTipo(rs.getString("TIPO"));
                moneda.setNombre(rs.getString("NOMBRE"));
                moneda.setNomenclatura(rs.getString("NOMENCLATURA"));
                moneda.setValorDolar(rs.getDouble("VALOR_DOLAR"));
                moneda.setVolatilidad(rs.getDouble("VOLATILIDAD"));
                moneda.setStock(rs.getDouble("STOCK"));
                monedas.add(moneda);
            }
        } catch (SQLException e) {
        	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            e.printStackTrace();
        }
        return monedas;
	}

	@Override
	public void actualizar(Moneda moneda) {
		 String sql = "UPDATE MONEDA SET TIPO=?, NOMBRE=?, VALOR_DOLAR=?, VOLATILIDAD=?, STOCK=? WHERE NOMENCLATURA=?";
	        Connection connection = ConexionBD.getConnection();
	        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, moneda.getTipo());
	            pstmt.setString(2, moneda.getNombre());
	            pstmt.setDouble(3, moneda.getValorDolar());
	            pstmt.setDouble(4, moneda.getVolatilidad());
	            pstmt.setDouble(5, moneda.getStock());
	            pstmt.setString(6, moneda.getNomenclatura());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	        	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	            e.printStackTrace();
	        }
		
	}



}