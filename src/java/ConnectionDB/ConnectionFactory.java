/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vfbeL
 */
public class ConnectionFactory {
    String driver = "jdbc:mysql";
    String url = "localhost";
    String port = "3306";
    String database = "controla_casa_db";
    String user = "root";
    String password = "vfbell0503";
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
           Connection connection =  DriverManager.getConnection(driver+"://"+url+":"+port+"/"+database,user,password);
            return connection;
        } catch (SQLException ex) {
            System.out.println("ERRO AO CONECTAR "+ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("ERRO AO CONECTAR 1+"+ex.getMessage());
            return null;
        }
    }
}
