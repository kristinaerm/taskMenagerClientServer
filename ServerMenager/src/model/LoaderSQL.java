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

    Connection con = null;

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

<<<<<<< HEAD
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
    public void addDataInTableTask(String idTask, String name, String time, String contacts, String description) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";;
            String login = "data";
            String password = "1";
=======



    public void addDataInTableTask(String idTask, String idTaskLog, String name, String time, String contacts, String description,String login,String password) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";;
//            String login = "data";
//            String password = "1";

>>>>>>> 5f8bff226d6da1aa57c50b6aa4fc6e96757087c0
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
//создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO task (id_task, name_task,description,contacts,time_task) VALUES (idTask, idTaskLog, name,description,contacts,time)");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }

    public void addDataInTableUser(String idUser, String passworduser, String loginuser) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
<<<<<<< HEAD
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String login = "data";
            String password = "1";
=======


            String url = "jdbc:oracle:thin:@localhost:1521:XE";;
//            String login = "data";
//            String password = "1";

>>>>>>> 5f8bff226d6da1aa57c50b6aa4fc6e96757087c0
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO users (id_user, login, password) VALUES (idUser, passworduser,loginuser)");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }

    public void addDataInTableUserTask(String idUser, String idTask) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
<<<<<<< HEAD
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String login = "data";
            String password = "1";
=======

            String url = "jdbc:oracle:thin:@localhost:1521:XE";
     
//            String login = "data";
//            String password = "1";

>>>>>>> 5f8bff226d6da1aa57c50b6aa4fc6e96757087c0
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO usertask (id_user,id_task) VALUES (idUser,idTask)");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }

    public void deleteDataInTableTask(String idTask) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
<<<<<<< HEAD
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String login = "data";
            String password = "1";
=======
            
            String url = "jdbc:oracle:thin:@localhost:1521:XE";;
//            String login = "data";
//            String password = "1";

>>>>>>> 5f8bff226d6da1aa57c50b6aa4fc6e96757087c0
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM task WHERE id_task="+idTask);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }
    
    public void deleteDataInTableUser(String idUser) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
<<<<<<< HEAD
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String login = "data";
            String password = "1";
=======

            String url = "jdbc:oracle:thin:@localhost:1521:XE";;
//            String login = "data";
//            String password = "1";

>>>>>>> 5f8bff226d6da1aa57c50b6aa4fc6e96757087c0
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM users WHERE idUser="+idUser);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }
    
    public void deleteDataInTableUserTask(String idUser, String idTask) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
<<<<<<< HEAD
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String login = "data";
            String password = "1";
=======

            String url = "jdbc:oracle:thin:@localhost:1521:XE";;
//            String login = "data";
//            String password = "1";

>>>>>>> 5f8bff226d6da1aa57c50b6aa4fc6e96757087c0
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM usertask WHERE (idUser = "+idUser+" AND id_task = "+idTask + ")");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }
    
        public void changeDataInTableTask(String idTask, String name, String time, String contacts, String description,String login,String password) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            
            String url = "jdbc:oracle:thin:@localhost:1521:XE";;
         
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
<<<<<<< HEAD
            st.executeUpdate("UPDATE task SET name_task = "+name+", description = "+description+",contacts = "+contacts+",time_task = "+time+"WHERE id_task = "+idTask);
=======
            st.executeUpdate("UPDATE task SET  name_task = "+name+", description = "+description+",contacts = "+contacts+",time_task = "+time+"WHERE id_task = "+idTask);
>>>>>>> 5f8bff226d6da1aa57c50b6aa4fc6e96757087c0
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }
   
    
    public void changeDataInTableUser(String idUser, String passworduser, String loginuser,String login,String password) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
 
            con = DriverManager.getConnection(url, login, password);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE users SET login = "+loginuser+", password = "+passworduser+" WHERE idUser = "+idUser);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }
}
