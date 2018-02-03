/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmenager;

import model.Record;
import exceptions.InvalidRecordFieldException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import view.SimpleTaskManager;
import static view.SimpleTaskManager.records;

/**
 *
 * @author USER
 */
public class Controller implements Serializable {

    public static ObjectInputStream in;
    public static ObjectOutputStream out;
    public static DefaultTableModel model;
    public static Record[] records;
    public static LinkedList<String> list = new LinkedList();

    public static void updateTable() throws IOException, ClassNotFoundException, InvalidRecordFieldException {
        String na, de, ti, co, id;
        out.writeChar('G');
        out.flush();
        int size = in.readInt();
        records = new Record[size];

        for (int i = 0; i < records.length; i++) {
            
                records[i]=(Record)in.readObject();
           //    records[i] = new Record(records[i].getName(), records[i].getDescription(), records[i].getTimeString(), records[i].getContacts());
                id = records[i].getId();
               records[i].setId(id);

               // na = in.readUTF();
              //  de = in.readUTF();
             //   ti = in.readUTF();
             //   co = in.readUTF();
//                records[i] = new Record(na, de, ti, co);
//                id = in.readUTF();
//                records[i].setId(id);

           
        }

        while (model.getRowCount() != 0) {
            for (int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
            }
        }
        for (int i = 0; i < records.length; i++) {
            model.addRow(new Object[]{i, records[i].getName(), records[i].getTimeString(), records[i].getDescription(), records[i].getContacts()});
        }
        SimpleTaskManager.records = records;
        SimpleTaskManager.updateNotification();
    }

    public static void addRecord(Record rec) throws IOException, ClassNotFoundException, InvalidRecordFieldException {
        out.writeChar('A');
        out.flush();
        out.writeObject(rec);
//        out.writeUTF(rec.getName());
//        out.writeUTF(rec.getDescription());
//        out.writeUTF(rec.getTimeString());
//        out.writeUTF(rec.getContacts());
        out.flush();
        updateTable();

    }

    public static String changeRecord(Record rec) throws IOException, ClassNotFoundException, InvalidRecordFieldException {
        out.writeChar('C');
        out.flush();
        out.writeObject(rec);
//        out.writeUTF(id);
//        out.writeUTF(n);
//        out.writeUTF(t);
//        out.writeUTF(d);
//        out.writeUTF(c);
        out.flush();
        String answer = in.readUTF();
        updateTable();
        if (answer.equals("OK")) {
            return "OK";
        } else {
            return answer;
        }

    }

    public static void deleteRecord(Record rec) throws IOException, ClassNotFoundException, InvalidRecordFieldException {
        out.writeChar('D');
        out.flush();
       // out.writeUTF(id);
        out.writeObject(rec);
        out.flush();
        updateTable();
    }

    public static void saveTaskLog() throws IOException {
        out.writeChar('W');
        out.flush();
    }

}
