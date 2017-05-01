package Controle;

import Entidade.Funcionario;
import Exception.ControlException;
import Exception.EntidadeException;
import Sql.Banco;
import Util.Erro;
import java.sql.Connection;

/**
 *
 * @author gabri
 */
public class FuncionarioControl {
    
    public boolean verificausuario(String login, String senha) throws ControlException{
        Connection con = Banco.getConexao();
        Erro e = new Erro();
        
        if(login==null || login.length()<=0 || login.length()>10){
            e.add("login incorreto!");
        }
        if(senha==null || senha.length()<=0 || senha.length()>8){
            e.add("senha incorreta!");
        }
        
        if(!e.isTemErro()){
            Funcionario f = new Funcionario();
            f.setLogin(login);
            f.setSenha(senha);
            try{
                f = f.select(con);
                if(f!=null && f.getCodigo()!=0)
                    return true;
                else
                    return false;
            }catch(EntidadeException ex){
                throw new ControlException(ex.getMessage());
                
            }
        }else
            return false;
    }
}
