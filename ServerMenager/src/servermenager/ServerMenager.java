/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermenager;

import exceptions.InvalidRecordFieldException;
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
    public static void main(String[] args) throws IOException, InvalidRecordFieldException, SAXException, ParserConfigurationException {
        // TODO code application logic here
        ServerSocket serverSoket = new ServerSocket(10);

        Socket client = serverSoket.accept();
        System.out.println("Соединение установлено");
        int countTask = 0;
        Record rec;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("other.xml");
        Loaders load = new Loaders();
        load.setLoaders('X');
        User u = load.readDocument(document);
        TaskLog currentTaskLog = u.getTaskLog();
        String name;
        String description;
        String time;
        String contacts;
        ObjectInputStream in = new ObjectInputStream(client.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
        while (true) {

            char cc = in.readChar();
            switch (cc) {
                case 'A'://add
                {
                   contacts = in.readUTF();
                    name = in.readUTF();
                    description = in.readUTF();
                    time = in.readUTF();
                    countTask = in.readInt();
                    System.out.println("Количество задач" + countTask);
                    for (int i = 0; i < countTask; i++) {
                        rec = new Record(name, description, time,contacts);
                       currentTaskLog.addRecord(rec);
                        currentTaskLog.updateTable();
                    }
                    
                    out.writeObject(currentTaskLog);
                    System.out.println("Задачи добавлены!");
                    break;
                }
                case 'D'://delete
                {
                    countTask = in.readInt();
                    currentTaskLog.deleteRecord(countTask);
                     currentTaskLog.updateTable();
                    out.writeObject(currentTaskLog);
                    System.out.println("Задачи удалены!");
                    break;
                }
                case 'C': //change
                {
                    contacts = in.readUTF();
                    name = in.readUTF();
                    description = in.readUTF();
                    time = in.readUTF();
                    countTask = in.readInt();
                    currentTaskLog.changeRecord(countTask, name, time,description, contacts);
                     currentTaskLog.updateTable();
                    out.writeObject(currentTaskLog);
                }
                case 'E'://execute
                {
                    countTask = in.readInt();
                    currentTaskLog.deleteRecord(countTask);
                    currentTaskLog.updateTable();
                    out.writeObject(currentTaskLog);
                    System.out.println("Выполнена!");
                    break;
                }
                case 'S'://set aside
                {
                     contacts = in.readUTF();
                    name = in.readUTF();
                    description = in.readUTF();
                    time = in.readUTF();
                    countTask = in.readInt();
                    if (countTask < currentTaskLog.getNumberOfRecords())
                    {
                    currentTaskLog.changeRecord(countTask, name, time,description, contacts);
                     currentTaskLog.updateTable();
                    out.writeObject(currentTaskLog);
                    }
                    break;
                }
            }

        }
    }

}
