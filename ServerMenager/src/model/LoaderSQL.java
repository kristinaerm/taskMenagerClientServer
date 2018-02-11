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

    private Connection con = null;
    private static final String LOGIN = "data";
    private static final String PASSWORD = "1";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";;
//метод записи в базу данных
    @Override
    public void addUser(Document document, User us) throws FileNotFoundException, TransformerException {
        try {
            clearDatabase(us);
            addDataInTableUser(us.getId(),us.getPassword(),us.getLogin());
            for(int i=0;i<us.getTaskLog().getNumberOfRecords();i++)
            {
                addDataInTableTask(us.getTaskLog().getRecord(i).getId(),us.getTaskLog().getRecord(i).getName(),us.getTaskLog().getRecord(i).getTimeString(),us.getTaskLog().getRecord(i).getContacts(),us.getTaskLog().getRecord(i).getDescription());
                addDataInTableUserTask(us.getId(),us.getTaskLog().getRecord(i).getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
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
//            String URL="jdbc:oracle:thin:@localhost:1521:XE";;
//            String LOGIN="data";
//            String PASSWORD="1";
//            con= DriverManager.getConnection(URL, LOGIN, PASSWORD);
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
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
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
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO users (id_user, login, PASSWORD) VALUES (idUser, passworduser,loginuser)");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }

    public void addDataInTableUserTask(String idUser, String idTask) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
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
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
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
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
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
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM usertask WHERE (idUser = "+idUser+" AND id_task = "+idTask + ")");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }
    
        public void changeDataInTableTask(String idTask, String name, String time, String contacts, String description) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE task SET name_task = "+name+", description = "+description+",contacts = "+contacts+",time_task = "+time+"WHERE id_task = "+idTask);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }
   
    
    public void changeDataInTableUser(String idUser, String passworduser, String loginuser) throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection Established");
            //создаем statement для запроса
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE users SET login = "+loginuser+", PASSWORD = "+passworduser+" WHERE idUser = "+idUser);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    
    }
    public void clearDatabase(User us) throws SQLException
    {
        deleteDataInTableUser(us.getId());
        for(int i=0;i<us.getTaskLog().getNumberOfRecords();i++)
        {
            deleteDataInTableTask(us.getTaskLog().getRecord(i).getId());
            deleteDataInTableUserTask(us.getId(), us.getTaskLog().getRecord(i).getId());
        }
    }
}
