/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager;

import interfaces.Loader;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
//import view.SimpleTaskManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import model.DataCheck;
import model.LoaderXML;
import model.Loaders;
import model.Record;
import model.User;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author USER
 */
public class TaskManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, FileNotFoundException, SAXException, ParserConfigurationException, IOException, TransformerException {

//        LinkedList<Record> list = new LinkedList<Record>();
//        list.add(new Record("task1", "tas", "2017-11-19 17:31", "18486"));
//        list.add(new Record("task2", "tas", "2017-11-19 17:30", "18486"));
//        list.add(new Record("task3", "tas", "2017-11-19 17:32", "18486"));
//        list.add(new Record("task4", "tas", "2017-11-18 17:43", "18486"));
//        list.add(new Record("task5", "tas", "2017-11-18 17:44", "18486"));
//        
//        User u = new User("i", "login", "pass", list);
//        //запись и считывание из xml
//        // Создается построитель документа
      // DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            // Создается дерево DOM документа из файла
        //Document document = documentBuilder.parse("Catalog.xml");
     /*   Document document = documentBuilder.parse("other.xml");
        Loaders load = new Loaders();
        load.setLoaders('X');
        User u = load.readDocument(document);

        SimpleTaskManager frame = new SimpleTaskManager(u, document);
        //  SimpleTaskManager frame = new SimpleTaskManager(u);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTray();

        frame.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent event) {

                // [ТУТ  ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
                try {
//                    User u = new User("1", null, null, list);
                    // Вызываем метод для записи,пока создала объект u

//            // Создается дерево DOM документа из файла
                    Document document1 = documentBuilder.parse("Catalog.xml");

                    load.addUser(document1, u);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(TaskManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });*/

    }

}
