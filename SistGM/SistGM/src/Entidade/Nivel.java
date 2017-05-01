/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import DAO.NivelDAO;
import Exception.DAOException;
import Exception.EntidadeException;
import java.sql.Connection;

/**
 *
 * @author gabri
 */
public class Nivel {
    private Integer codigo;
    private String nome;

    public Nivel() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Nivel select(Connection con) throws EntidadeException{
        try{
            Nivel n = new NivelDAO().select(this, con);
            return n;
        }catch(DAOException ex){
            throw new EntidadeException(ex.getMessage());
        }
    }
    
}
