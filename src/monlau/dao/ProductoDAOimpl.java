package monlau.dao;

import monlau.model.Producto;
import java.sql.*;

public class ProductoDAOimpl implements ProductoDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/API";
    static final String DB_USER = "root";
    static final String DB_PWD = "";

    private void registerDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR");
            ex.printStackTrace();
        }
    }

    public void insert(Producto producto) {
        Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into producto values ("
                    + producto.getId() + ",'"
                    + producto.getNombre() + "',"
                    + producto.getPrecio() + ");");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void delete(Integer id) {
Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            try ( PreparedStatement ps = conn.prepareStatement("delete from producto where id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void update(Producto producto) {
Connection conn = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            try ( PreparedStatement ps = conn.prepareStatement("update producto set name = ?, price = ? where id = ?")) {
                ps.setString(1, producto.getNombre());
                ps.setDouble(2, producto.getPrecio());
                ps.setInt(3, producto.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Producto read(Integer id) {
        Connection conn = null;
        Producto prod = null;
        try {
            registerDriver();
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            try ( PreparedStatement ps = conn.prepareStatement("select * from producto where id = ?")) {
                ps.setInt(1, id);
                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        prod = new Producto(id, rs.getString("nombre"), rs.getDouble("precio"));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
        return prod;

    }
}