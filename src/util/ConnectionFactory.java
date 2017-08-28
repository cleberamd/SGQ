/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Uma f√°brica de conex√µes.
 *
 * @author David Buzatto
 */
public class ConnectionFactory {

    
    public static Connection getConexao() throws Exception {
        java.sql.Connection conexao = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env/");
            javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup("jdbc/BDE");
            conexao = (java.sql.Connection) ds.getConnection();
        } catch (NamingException e) {
            throw new Exception("N„o foi possÌvel encontrar o nome da conex„o do banco.", e);
        } catch (SQLException e) {
            throw new Exception("Ocorreu um erro de SQL.", e);
        }
        return conexao;
    }

}
