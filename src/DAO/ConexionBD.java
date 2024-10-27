package DAO;

import java.sql.*;

public class ConexionBD {
	
    protected static Connection c = null;
    private static final String BD_URL = "jdbc:sqlite:walletDB.db";
    
    public static Connection getConnection() {
        if (c == null) {
            try {
                c = DriverManager.getConnection(BD_URL);
                System.out.println("Conexión realizada");
                creaciónDeTablasEnBD(c); // Llamamos a la creación de tablas aquí
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        return c;
    }
    
    public static void closeConnection() {
        try {
            if ((c != null) && !(c.isClosed())) {
                c.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
    private static void creaciónDeTablasEnBD(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        
        String sql = "CREATE TABLE IF NOT EXISTS MONEDA " 
                + "(" 
                + " TIPO       VARCHAR(1)    NOT NULL, "
                + " NOMBRE     VARCHAR(50)    NOT NULL, " 
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY   NOT NULL, "
                + " VALOR_DOLAR REAL          NOT NULL, " 
                + " VOLATILIDAD REAL          NULL " 
                + ")";
        stmt.executeUpdate(sql);
        
        sql = "CREATE TABLE IF NOT EXISTS ACTIVO" 
                + "(" 
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY NOT NULL, "
                + " CANTIDAD     REAL          NOT NULL " 
                + ")";
        stmt.executeUpdate(sql);
        
        sql = "CREATE TABLE IF NOT EXISTS TRANSACCION" 
                + "(" 
                + " RESUMEN VARCHAR(1000)   NOT NULL, "
                + " FECHA_HORA DATETIME     NOT NULL " 
                + ")";
        stmt.executeUpdate(sql);
        
        sql = "CREATE TABLE IF NOT EXISTS STOCK " 
                + "("
                + " NOMENCLATURA VARCHAR(10)  PRIMARY KEY NOT NULL, "
                + " CANTIDAD     REAL          NOT NULL "
                + ")";
        stmt.executeUpdate(sql);
        
        stmt.close();
    }
}
