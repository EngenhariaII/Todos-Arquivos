/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidade.Nivel;
import Exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class NivelDAO {
    private String select= "select * from nivel";
    
    public Nivel select(Nivel obj, Connection con) throws DAOException{
        if(con!=null){
            PreparedStatement ps = null;
            ResultSet rs = null;
            int cont = 0;
            boolean ultimo = false;
            
            if(obj.getCodigo()!=null && obj.getCodigo()!=0){
                select+=" where niv_codigo = ?";
                ultimo = true;
            }
            
            if(obj.getNome()!=null && !obj.getNome().isEmpty()){
                if(ultimo){
                    select += " and fun_nome = ?";
                }else{
                    select+= " where fun_nome = ?";
                }
            }
            
            try{
                ps = con.prepareStatement(select);
                if(obj.getCodigo()!=null && obj.getCodigo()!=0)
                    ps.setInt(++cont, obj.getCodigo());
                if(obj.getNome()!=null && !obj.getNome().isEmpty())
                    ps.setString(++cont, obj.getNome());
                rs = ps.executeQuery();
                
                if(rs.next()){
                    Nivel n = new Nivel();
                    n.setCodigo(rs.getInt("niv_codigo"));
                    n.setNome(rs.getString("niv_nome"));
                    return n;
                }else{
                    return null;
                }
            }catch(SQLException ex){
                throw new DAOException(ex.getMessage());
            }    
        }else{
            throw new DAOException("Erro na conexão!");
        }
    }
}
