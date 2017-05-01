/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidade.Funcionario;
import Entidade.Nivel;
import Exception.DAOException;
import Exception.EntidadeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class FuncionarioDAO {
    
    private String select = "SELECT * FROM funcionario ";
    private String insert = "INSERT INTO funcionario(fun_nome, fun_endereco, fun_telefone, fun_celular," +
                            "fun_dtadmis, fun_dtdemis, fun_login, fun_senha, fun_cpf, niv_codigo)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    
    private String update = "UPDATE funcionario SET fun_nome = ?, fun_endereco = ?, fun_telefone = ?, fun_celular = ?," +
                            " fun_dtadmis = ?, fun_dtdemis = ?, fun_login = ?, fun_senha = ?, fun_cpf = ?," +
                            " niv_codigo = ?" +
                            " WHERE fun_codigo = ?";
    
    private String delete = "DELETE FROM funcionario WHERE fun_codigo = ?";
    
     public Funcionario select(Funcionario obj, Connection con) throws DAOException, EntidadeException {
        if (con != null) {

            PreparedStatement ps = null;
            ResultSet rs = null;

            int cont = 0;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=0){
                select += " where fun_codigo = ?";
                ultimo = true;
            }

            if (obj.getLogin() != null && !obj.getLogin().isEmpty()) {
                if (ultimo) {
                    select += " and fun_login = ?";
                } else {
                    select += " where fun_login = ?";
                    ultimo = true;
                }
            }

            if (obj.getSenha() != null && !obj.getSenha().isEmpty()) {
                if (ultimo) {
                    select += " and fun_senha = ?";
                } else {
                    select += " where fun_senha = ?";
                }
            }

            try {
                ps = con.prepareStatement(select);

                if (obj.getCodigo() != 0) {
                    ps.setInt(++cont, obj.getCodigo());
                }

                if (obj.getLogin() != null && !obj.getLogin().isEmpty()) {
                    ps.setString(++cont, obj.getLogin());
                }
                
                if (obj.getSenha() != null && !obj.getSenha().isEmpty()) {
                    ps.setString(++cont, obj.getSenha());
                }

                rs = ps.executeQuery();

                if (rs.next()) {
                    Funcionario f = new Funcionario();

                    f.setCodigo(rs.getInt("fun_codigo"));
                    f.setNome(rs.getString("fun_nome"));
                    f.setEndereco(rs.getString("fun_endereco"));
                    f.setTelefone(rs.getString("fun_telefone"));
                    f.setCelular(rs.getString("fun_celular"));
                    f.setDtadmis(rs.getDate("fun_dtadmis"));
                    f.setDtdemis(rs.getDate("fun_dtdemis"));
                    f.setLogin(rs.getString("fun_login"));
                    f.setSenha(rs.getString("fun_senha"));
                    
                    Nivel n = new Nivel();
                    n.setCodigo(rs.getInt("niv_codigo"));
                    f.setNivel(n.select(con));
                    return f;
                } 
                else {
                    return null;
                }
            } 
            catch (SQLException e) {
                throw new DAOException("Erro :" + e.getErrorCode()
                        + "\nDescrição: " + e.getLocalizedMessage(), e);
            } 
        } 
        else {
            throw new DAOException("Erro na conexão");
        }
    }
     
     public void insert(Funcionario obj, Connection con) throws DAOException{
         if (con != null) {

            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(insert);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getEndereco());
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getCelular());
                ps.setDate(++cont, new java.sql.Date(obj.getDtadmis().getTime()));
                if(obj.getDtdemis()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtadmis().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setString(++cont, obj.getLogin());
                ps.setString(++cont, obj.getSenha());
                ps.setString(++cont, obj.getCpf());
                ps.setInt(++cont, obj.getNivel().getCodigo());
                ps.executeUpdate();
            }catch(SQLException e){
                throw new DAOException(e.getMessage());
            }
         }else{
             throw new DAOException("Erro na conexão!");
         }   
     }
     
     public void update(Funcionario obj, Connection con) throws DAOException{
         if (con != null) {

            PreparedStatement ps = null;
            int cont = 0;
            try{
                ps = con.prepareStatement(update);
                ps.setString(++cont, obj.getNome());
                ps.setString(++cont, obj.getEndereco());
                ps.setString(++cont, obj.getTelefone());
                ps.setString(++cont, obj.getCelular());
                ps.setDate(++cont, new java.sql.Date(obj.getDtadmis().getTime()));
                if(obj.getDtdemis()!=null)
                    ps.setDate(++cont, new java.sql.Date(obj.getDtadmis().getTime()));
                else
                    ps.setNull(++cont, java.sql.Types.DATE);
                ps.setString(++cont, obj.getLogin());
                ps.setString(++cont, obj.getSenha());
                ps.setString(++cont, obj.getCpf());
                ps.setInt(++cont, obj.getNivel().getCodigo());
                ps.setInt(++cont, obj.getCodigo());
                ps.executeUpdate();
            }catch(SQLException e){
                throw new DAOException(e.getMessage());
            }
         }else{
             throw new DAOException("Erro na conexão!");
         }
     }
     
     public void delete(Funcionario obj, Connection con) throws DAOException{
         if(con!=null){
             PreparedStatement ps = null;
             try{
                 ps = con.prepareStatement(delete);
                 ps.setInt(1, obj.getCodigo());
                 ps.executeUpdate();
             }catch(SQLException e){
                 throw new DAOException(e.getMessage());
             }
         }else{
             throw new DAOException("Erro na conexão!");
         }
     }
     
     public List<Funcionario> lista(Funcionario obj, Connection con) throws DAOException, EntidadeException{
         if (con != null) {

            List<Funcionario> lista = new ArrayList<>();
            PreparedStatement ps = null;
            ResultSet rs = null;

            int cont = 0;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=0){
                select += " where fun_codigo = ?";
                ultimo = true;
            }

            if (obj.getLogin() != null && !obj.getLogin().isEmpty()) {
                if (ultimo) {
                    select += " and fun_login = ?";
                } else {
                    select += " where fun_login = ?";
                    ultimo = true;
                }
            }

            if (obj.getSenha() != null && !obj.getSenha().isEmpty()) {
                if (ultimo) {
                    select += " and fun_senha = ?";
                } else {
                    select += " where fun_senha = ?";
                }
            }

            try {
                ps = con.prepareStatement(select);

                if (obj.getCodigo() != 0) {
                    ps.setInt(++cont, obj.getCodigo());
                }

                if (obj.getLogin() != null && !obj.getLogin().isEmpty()) {
                    ps.setString(++cont, obj.getLogin());
                }
                
                if (obj.getSenha() != null && !obj.getSenha().isEmpty()) {
                    ps.setString(++cont, obj.getSenha());
                }

                rs = ps.executeQuery();

                if (rs.next()) {
                    Funcionario f = new Funcionario();

                    f.setCodigo(rs.getInt("fun_codigo"));
                    f.setNome(rs.getString("fun_nome"));
                    f.setEndereco(rs.getString("fun_endereco"));
                    f.setTelefone(rs.getString("fun_telefone"));
                    f.setCelular(rs.getString("fun_celular"));
                    f.setDtadmis(rs.getTimestamp("fun_dtadmis"));
                    f.setDtdemis(rs.getDate("fun_dtdemis"));
                    f.setLogin(rs.getString("fun_login"));
                    f.setSenha(rs.getString("fun_senha"));
                    
                    Nivel n = new Nivel();
                    n.setCodigo(rs.getInt("niv_codigo"));
                    f.setNivel(n.select(con));
                    lista.add(f);
                } 
                return lista;
            } 
            catch (SQLException e) {
                throw new DAOException("Erro :" + e.getErrorCode()
                        + "\nDescrição: " + e.getLocalizedMessage(), e);
            } 
        } 
        else {
            throw new DAOException("Erro na conexão");
        }
     }
}
