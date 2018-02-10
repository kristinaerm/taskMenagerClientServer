/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.Loader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Кристина
 */
public class LoaderSQL implements Loader {
   Connection con =null;
    @Override
    public void addUser(Document document, User us) throws FileNotFoundException, TransformerException {
       
    }

    @Override
    public User readDocument(Document document) throws ParserConfigurationException, SAXException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeDocument(Document document) throws TransformerConfigurationException, FileNotFoundException, TransformerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
//    public Connection connection() throws SQLException
//    {
//        Connection con =null;
//        try {
//            Class.forName("oracle.jdbc.OracleDriver");
//            String url="jdbc:oracle:thin:@localhost:1521:XE";;
//            String login="data";
//            String password="1";
//            con= DriverManager.getConnection(url, login, password);
//            System.out.println("Connection Established");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            con.close();
//        }
//        return con;
//    }
     public void addDataInTableTask(String idTask,String idTaskLog,String name,String time,String contacts,String description) throws SQLException
     {
       try {
           Class.forName("oracle.jdbc.OracleDriver");
      
            String url="jdbc:oracle:thin:@localhost:1521:XE";;
            String login="data";
            String password="1";
            con= DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
//создаем statement для запроса
        Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO task (id_task, id_taskLog, name_task,description,contacts,time_task) VALUES (idTask, idTaskLog, name,description,contacts,time)");
        st.executeUpdate("INSERT INTO usertask (id_task) VALUES (idTask)");
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
       }
       con.close();
     }
       
      

     public void addDataInTableUser(String idUser,String passworduser,String loginuser) throws SQLException
{
        try {
           Class.forName("oracle.jdbc.OracleDriver");
      
            String url="jdbc:oracle:thin:@localhost:1521:XE";;
            String login="data";
            String password="1";
            con= DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
//создаем statement для запроса
        Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO task (id_user, login, password) VALUES (idUser, passworduser,loginuser)");
        st.executeUpdate("INSERT INTO usertask (id_user) VALUES (idUser)");
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
       }
       con.close();
}
    
}