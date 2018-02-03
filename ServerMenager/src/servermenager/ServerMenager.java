/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermenager;

import exceptions.InvalidRecordFieldException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import model.Loaders;
import model.Record;
import model.TaskLog;
import model.User;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Кристина
 */
public class ServerMenager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidRecordFieldException, SAXException, ParserConfigurationException, ClassNotFoundException, FileNotFoundException, TransformerException {
        // TODO code application logic here
        ServerSocket serverSoket = new ServerSocket(1024);

        Socket client = serverSoket.accept();
        System.out.println("Соединение установлено");
        int countTask = 0;
        // Record []rec;
        Record rec;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("other.xml");
        Loaders load = new Loaders();
        load.setLoaders('X');
        User u = load.readDocument(document);
        TaskLog currentTaskLog = u.getTaskLog();

        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

    //    String na, de, ti, co, id;

        char cc;
        while (true) {

            cc = in.readChar();
            switch (cc) {
                //GET RECORDS
                case 'G': {
                    int g = currentTaskLog.getNumberOfRecords();
                    out.writeInt(currentTaskLog.getNumberOfRecords());
                    //out.flush();
                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
                        out.writeObject(currentTaskLog.getRecord(i));
//                        out.writeUTF(currentTaskLog.getRecord(i).getName());
//                        out.writeUTF(currentTaskLog.getRecord(i).getDescription());
//                        out.writeUTF(currentTaskLog.getRecord(i).getTimeString());
//                        out.writeUTF(currentTaskLog.getRecord(i).getContacts());
//                        out.writeUTF(currentTaskLog.getRecord(i).getId());
                       
                    }
                     out.flush();
                    System.out.println("Задачи переданы на клиент!");
                    break;
                }
                //ADD
                case 'A': {
                   rec = (Record) in.readObject();
//                    na = in.readUTF();
//                    de = in.readUTF();
//                    ti = in.readUTF();
//                    co = in.readUTF();
//                    rec = new Record(na, de, ti, co);
                    currentTaskLog.addRecord(rec);

//                    out.writeInt(currentTaskLog.getNumberOfRecords());
//                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
//                        out.writeObject(currentTaskLog.getRecord(i));
//                    }
                    System.out.println("Задачи добавлены!");
                    break;
                }
                //Delete
                case 'D': {
                    rec=(Record) in.readObject();
                   String id = rec.getId();
                    currentTaskLog.deleteRecord(id);
//                    out.writeInt(currentTaskLog.getNumberOfRecords());
//                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
//                        out.writeObject(currentTaskLog.getRecord(i));
//                    }

                    System.out.println("Задачи удалены!");
                    break;
                }
                //Change
                case 'C': {

//                    id = in.readUTF();
//                    String name = in.readUTF();
//                    String time = in.readUTF();
//                    String des = in.readUTF();
//                    String cont = in.readUTF();
                    rec=(Record) in.readObject();
                    try {
                        
                        currentTaskLog.changeRecord(rec.getId(), rec.getName(), rec.getTimeString(), rec.getDescription(), rec.getContacts());
                        out.writeUTF("OK");
                    } catch (InvalidRecordFieldException | IndexOutOfBoundsException ex) {
                        out.writeUTF(ex.getMessage());
                    }
                //  out.writeInt(currentTaskLog.getNumberOfRecords());
//                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
//                        out.writeObject(currentTaskLog.getRecord(i));
//                    }
                    out.flush();
                    break;
                }

                case 'W'://write
                {
                    Document document1 = documentBuilder.parse("Catalog.xml");
                    load.addUser(document1, u);
                    break;
                }

            }
            out.flush();

        }

    }

}
