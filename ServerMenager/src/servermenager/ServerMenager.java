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
import java.util.Date;
import java.util.LinkedList;
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
        char cc;
        while (true) {

            cc = in.readChar();
            switch (cc) {
                //GET RECORDS
                case 'G':
                {
                    int g=currentTaskLog.getNumberOfRecords();
                    out.writeInt(currentTaskLog.getNumberOfRecords());
                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
                        out.writeObject(currentTaskLog.getRecord(i));
                    }
                    System.out.println("Задачи переданы на клиент!");
                    break;
                }
                //ADD
                case 'A':
                {
                    rec = (Record) in.readObject();
                    currentTaskLog.addRecord(rec);

//                    out.writeInt(currentTaskLog.getNumberOfRecords());
//                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
//                        out.writeObject(currentTaskLog.getRecord(i));
//                    }
                    System.out.println("Задачи добавлены!");
                    break;
                }
                //Delete
                case 'D':
                {
                    countTask = in.readInt();
                    currentTaskLog.deleteRecord(countTask);
//                    out.writeInt(currentTaskLog.getNumberOfRecords());
//                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
//                        out.writeObject(currentTaskLog.getRecord(i));
//                    }

                    System.out.println("Задачи удалены!");
                    break;
                }
                //Change
                case 'C': 
                {

                    countTask = in.readInt();
                    String name = in.readUTF();
                    String time = in.readUTF();
                    String des = in.readUTF();
                    String cont = in.readUTF();
                    try{
                        currentTaskLog.changeRecord(countTask, name, time, des, cont);
                        out.writeUTF("OK");
                    }
                    catch (InvalidRecordFieldException ex){
                        out.writeUTF(ex.getMessage());
                    }
//                    out.writeInt(currentTaskLog.getNumberOfRecords());
//                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
//                        out.writeObject(currentTaskLog.getRecord(i));
//                    }
                    break;
                }
//                //Execute
//                case 'E':
//                {
//                    countTask = in.readInt();
//                    currentTaskLog.deleteRecord(countTask);
//                    out.writeInt(currentTaskLog.getNumberOfRecords());
////                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
////                        out.writeObject(currentTaskLog.getRecord(i));
////                    }
//                    System.out.println("Выполнена!");
//                    break;
//                }
//                //Set aside
//                case 'S':
//                {
//
//                    countTask = in.readInt();
//                    rec = (Record) in.readObject();
//                    if (countTask < currentTaskLog.getNumberOfRecords()) {
//                        currentTaskLog.changeRecord(countTask, rec.getName(), rec.getTimeString(), rec.getDescription(), rec.getContacts());
//                        out.writeInt(currentTaskLog.getNumberOfRecords());
////                        for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
////                            out.writeObject(currentTaskLog.getRecord(i));
////                        }
//                    }
//                    break;
//                }
//                case 'R'://read
//                {
//
//                    break;
//                }
                case 'W'://write
                {
                    Document document1 = documentBuilder.parse("Catalog.xml");
                    load.addUser(document1, u);
                    break;
                }
//                case 'r'://ПОЛУЧЕНИЕ ЗАПИСЕЙ ВСЕХ
//                {
//                    for (int i = 0; i < currentTaskLog.getNumberOfRecords(); i++) {
//                            out.writeObject(currentTaskLog.getRecord(i));
//                        }
//                    break;
//                }
            }
            out.flush();
     
        }
    }

}
