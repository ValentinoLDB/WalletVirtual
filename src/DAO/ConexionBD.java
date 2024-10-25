package DAO;

import java.sql.*;

public class ConexionBD {
	
    private static Connection c=null;
    public static Connection getConnection() {
    	if(c==null) {
		    try {
		       c = DriverManager.getConnection("jdbc:sqlite:walletDB.db");
		    } catch ( SQLException e ) {
		       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		       System.exit(0);
		    }
		    System.out.println("Opened database successfully");
    	}
    	return c;
    }
    
    public static void closeConnection() {
    	try {
	    	if((c!= null) && !(c.isClosed())) {
	    		c.close();
	    	}
    	}
    	catch (SQLException e) {
    		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
    	}
    }
    
    private static void creaciónDeTablasEnBD(Connection connection) throws SQLException {
		Statement stmt;
		stmt = connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS MONEDA " 
                + "(" 
                + " TIPO       VARCHAR(1)    NOT NULL, "
				+ " NOMBRE       VARCHAR(50)    NOT NULL, " 
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
				+ " VALOR_DOLAR	REAL     NOT NULL, " 
				+ " VOLATILIDAD	REAL     NULL, "
				+ " STOCK	REAL     NULL "  + ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE IF NOT EXISTS ACTIVO" //En la que nos dan, activo lo separa en fiat y cripto, acá los unifiqué
				+ "(" 
				+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "//si respetamos el original, debemos agregar DIRECCION
				+ " CANTIDAD	REAL    NOT NULL " + ")";
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE IF NOT EXISTS TRANSACCION" 
                + "(" 
                + " RESUMEN VARCHAR(1000)   NOT NULL, "
                + " FECHA_HORA		DATETIME  NOT NULL " + ")";
		stmt.executeUpdate(sql);
		sql= "CREATE TABLE IF NOT EXISTS STOCK"
				+ " NOMENCLATURA VARCHAR(10)  PRIMARY KEY     NOT NULL, "
				+ " CANTIDAD	REAL    NOT NULL " + ")";
		stmt.close();
	}
}
