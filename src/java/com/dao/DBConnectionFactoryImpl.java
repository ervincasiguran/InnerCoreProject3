/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Garfield
 */
public class DBConnectionFactoryImpl extends DBConnectionFactory{

    @Override
    public Connection getConnection() {
        try {
            Class.forName(getDriverName());
            Connection conn = 
                   DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            //Context ctx = new InitialContext();
            //DataSource ds = (DataSource) ctx.lookup(getDataSourceName());
            //Connection conn = ds.getConnection();
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnectionFactoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
    
}
