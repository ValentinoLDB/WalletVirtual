package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import Interfaces.MonedaDAO;
import Modelo.Moneda;

public class MonedaDAOimpl implements MonedaDAO {

	@Override
	public void crear(Moneda moneda) {
		String sql = "INSERT INTO MONEDA (TIPO, NOMBRE, NOMENCLATURA, VALOR_DOLAR, VOLATILIDAD) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConexionBD.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, moneda.getTipo());
            pstmt.setString(2, moneda.getNombre());
            pstmt.setString(3, moneda.getNomenclatura());
            pstmt.setDouble(4, moneda.getValorDolar());
            pstmt.setDouble(5, moneda.getVolatilidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Moneda> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Moneda entidad) {
		// TODO Auto-generated method stub
		
	}



}
