package Entidade;

import DAO.FuncionarioDAO;
import Exception.DAOException;
import Exception.EntidadeException;
import java.sql.Connection;
import java.util.Date;

/**
 *
 * @author gabri
 */
public class Funcionario {
    private int codigo;
    private String nome;
    private String endereco;
    private String telefone;
    private String celular;
    private Date dtadmis;
    private Date dtdemis;
    private String login;
    private String senha;
    private String cpf;
    private Nivel nivel;

    public Funcionario() {
        nivel = new Nivel();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getDtadmis() {
        return dtadmis;
    }

    public void setDtadmis(Date dtadmis) {
        this.dtadmis = dtadmis;
    }

    public Date getDtdemis() {
        return dtdemis;
    }

    public void setDtdemis(Date dtdemis) {
        this.dtdemis = dtdemis;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    
    public Funcionario select(Connection con) throws EntidadeException{
        try {
            Funcionario f = new FuncionarioDAO().select(this, con);
            return f;
        } 
        catch (DAOException ex) {
            throw new EntidadeException(ex.getMessage()); 
        }
    }
    
    public void insert(Connection con) throws EntidadeException{
        try {
            new FuncionarioDAO().insert(this, con);
        } 
        catch (DAOException ex) {
            throw new EntidadeException(ex.getMessage()); 
        }
    }
    
    public void update(Connection con) throws EntidadeException{
        try {
            new FuncionarioDAO().update(this, con);
        } 
        catch (DAOException ex) {
            throw new EntidadeException(ex.getMessage()); 
        }
    }
    
    public void delete(Connection con) throws EntidadeException{
        try {
            new FuncionarioDAO().delete(this, con);
        } 
        catch (DAOException ex) {
            throw new EntidadeException(ex.getMessage()); 
        }
    }
}
