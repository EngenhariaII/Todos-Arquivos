package Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Banco {


    public static final Connection getConexao() {
        Connection con = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Sql/config");

            Class.forName(bundle.getString("driver"));
            return DriverManager.getConnection(
                    bundle.getString("url"),
                    bundle.getString("user"),
                    bundle.getString("pass")
            );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final void fechaTudo(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static final void fechaTudo(Statement st, Connection conn) {
        fechaTudo(null, st, conn);
    }

    public static final void fechaTudo(Connection conn) {
        fechaTudo(null, null, conn);
    }

}
