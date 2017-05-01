package Entidade;

import DAO.EmpresaDAO;
import DAO.FuncionarioDAO;
import Exception.DAOException;
import Exception.EntidadeException;
import java.sql.Connection;

/**
 *
 * @author Gabriel Jesus
 */
public class Empresa {
    private Integer codigo;
    private String nome;
    private String nomefantasia;
    private String telefone;
    private String email;
    private String site;
    private String endereco;

    public Empresa() {
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

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Empresa select(Connection con) throws EntidadeException{
        try {
            Empresa e = new EmpresaDAO().select(con);
            return e;
        } 
        catch (DAOException ex) {
            throw new EntidadeException(ex.getMessage()); 
        }
    }
}
