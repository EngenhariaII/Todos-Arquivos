package Controle;

import Entidade.Empresa;
import Exception.ControlException;
import Exception.EntidadeException;
import Sql.Banco;
import Util.Erro;
import java.sql.Connection;

/**
 *
 * @author Gabriel Jesus
 */
public class EmpresaControl {
    public boolean verificaempresa() throws ControlException{
        Connection con = Banco.getConexao();
        try{
            Empresa e = new Empresa().select(con);
            if(e!=null && e.getCodigo()!=0)
                return true;
            else
                return false;
        }catch(EntidadeException ex){
            throw new ControlException(ex.getMessage());

        }
    }
}
