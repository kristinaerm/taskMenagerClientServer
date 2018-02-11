/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.InvalidRecordFieldException;
import interfaces.Loader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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

    @Override
    public void addUser(Document document, User us) throws FileNotFoundException, TransformerException {

    }

    public User readDocument(Document document, String log, String pass) throws SQLException, InvalidRecordFieldException {
        User user = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection Established");
            Statement st;
            ResultSet rs;
            try {
                st = con.createStatement();
                rs = st.executeQuery("SELECT id_user FROM users WHERE login = " + log + " and password = " + pass);
                String id_user = rs.getString("id_user");
                rs.close();
                st.close();
                
                st = con.createStatement();
                rs = st.executeQuery("SELECT id_task FROM usertask WHERE id_user = " + id_user);
                LinkedList <String> id_tasks = new LinkedList();
                while (rs.next()) {
                    id_tasks.add(rs.getString("id_task"));
                }
                rs.close();
                st.close();
                
                LinkedList<Record> records = new LinkedList();
                Record rec;
                for (int i = 0; i < id_tasks.size(); i++) {
                    st = con.createStatement();
                    rs = st.executeQuery("SELECT name_task,description,contacts,time_task FROM task WHERE id_task = " + id_tasks.get(i));
                    rec = new Record(rs.getString("name_task"), rs.getString("description"), rs.getString("time_task"), rs.getString("contacts"));
                    records.add(rec);
                    rs.close();
                    st.close();
                }
                
                user = new User(id_user, log, pass, records);
                
            } finally {
                con.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
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
            st.executeUpdate("INSERT INTO task (id_task, name_task,description,contacts,time_task) VALUES ("+idTask+", "+name+", "+description+", "+contacts+","+time+")");
            st.close();
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
            st.executeUpdate("INSERT INTO users (id_user, login, password) VALUES ("+idUser+", "+passworduser+","+loginuser+")");
            st.close();
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
            st.executeUpdate("INSERT INTO usertask (id_user,id_task) VALUES ("+idUser+","+idTask+")");
            st.close();
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
            st.close();
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
            st.close();
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
            st.close();
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
            st.close();
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
            st.executeUpdate("UPDATE users SET login = "+loginuser+", password = "+passworduser+" WHERE idUser = "+idUser);
            st.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoaderSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.close();
    }

    @Override
    public User readDocument(Document document) throws ParserConfigurationException, SAXException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
