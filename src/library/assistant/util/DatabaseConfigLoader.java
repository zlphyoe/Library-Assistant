/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Zaw Lwin Phyo
 */
public class DatabaseConfigLoader {
    public void saveDatabaseProperties(DatabaseProperty dbProperty){
        	Properties propertie = new Properties();
        try(OutputStream fos=new FileOutputStream("dbConfig.properties")){
        	propertie.setProperty("host", dbProperty.getHost());
                propertie.setProperty("port",dbProperty.getPort());
        	propertie.setProperty("username", dbProperty.getUser());
        	propertie.setProperty("password", dbProperty.getPassword());
        	
        	propertie.store(fos, "for database configuration");
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    public DatabaseProperty getDatabaseProperties(){
        	Properties propertie = new Properties();
                DatabaseProperty dbProperty=null;
        try(InputStream fis=new FileInputStream("dbConfig.properties")){
        	propertie.load(fis);
        	
                String host=propertie.getProperty("host");
                 String port=propertie.getProperty("port");
                 String user=propertie.getProperty("username");
               
               
                String password=propertie.getProperty("password");
                
                dbProperty=new DatabaseProperty(host, user, password, port);
                
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return dbProperty;

    }
}
