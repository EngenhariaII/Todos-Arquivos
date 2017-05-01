package DAO;

import Entidade.Empresa;
import Exception.DAOException;
import Exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel Jesus
 */
public class EmpresaDAO {
    private String select = "Select * from empresa";
    
    public Empresa select(Connection con) throws DAOException, EntidadeException {
        if (con != null) {

            PreparedStatement ps = null;
            ResultSet rs = null;

            int cont = 0;
            boolean ultimo = false;

            try {
                ps = con.prepareStatement(select);
                rs = ps.executeQuery();

                if (rs.next()) {
                    Empresa e = new Empresa();

                    e.setCodigo(rs.getInt("emp_codigo"));
                    e.setNome(rs.getString("emp_nome"));
                    e.setNomefantasia(rs.getString("emp_nomefantasia"));
                    e.setTelefone(rs.getString("emp_telefone"));
                    e.setEmail(rs.getString("emp_email"));
                    e.setSite(rs.getString("emp_site"));
                    e.setEndereco(rs.getString("emp_endereco"));
                    
                    return e;
                } 
                else {
                    return null;
                }
            } 
            catch (SQLException ex) {
                throw new DAOException("Erro :" + ex.getErrorCode()
                        + "\nDescrição: " + ex.getLocalizedMessage(), ex);
            } 
        } 
        else {
            throw new DAOException("Erro na conexão");
        }
    }
}
