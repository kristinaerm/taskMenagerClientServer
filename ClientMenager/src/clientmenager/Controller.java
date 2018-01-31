/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmenager;

import exceptions.InvalidRecordFieldException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import view.SimpleTaskManager;
import static view.SimpleTaskManager.records;

/**
 *
 * @author USER
 */
public class Controller {
    
    public static ObjectInputStream in;    
    public static ObjectOutputStream out;
    public static DefaultTableModel model;
    public static Record[] records;
    
    public static void updateTable() throws IOException, ClassNotFoundException{
        String na, de,ti,co,id;
        out.writeChar('G');
        out.flush();
        int size = in.readInt();
<<<<<<< HEAD
        records = new Record[size];
=======

       records=new Record[size];

>>>>>>> 1d82808ccd5b756aba45999b3f4c06b69110134b
        for (int i=0; i<size; i++){
            try {
                //            records[i]=(Record)in.readObject();
                na = in.readUTF();
                de = in.readUTF();
                ti = in.readUTF();
                co = in.readUTF();
                records[i] = new Record(na,de,ti,co);
                id = in.readUTF();
                records[i].setId(id);
            } catch (InvalidRecordFieldException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public static void addRecord(Record rec) throws IOException, ClassNotFoundException {
        out.writeChar('A');
        out.flush();
        out.writeUTF(rec.getName());
        out.writeUTF(rec.getDescription());
        out.writeUTF(rec.getTimeString());
        out.writeUTF(rec.getContacts());
        out.flush();
        updateTable();

    }
    
    public static String changeRecord(String id, String n, String t, String d, String c) throws IOException, ClassNotFoundException{
        out.writeChar('C');
        out.flush();
        out.writeUTF(id);
        out.writeUTF(n);
        out.writeUTF(t);
        out.writeUTF(d);
        out.writeUTF(c);
        out.flush();        
        String answer = in.readUTF();
        updateTable();
        if (answer.equals("OK")) return "OK";
        else return answer;

    }
    
    public static void  deleteRecord(String id) throws IOException, ClassNotFoundException{
        out.writeChar('D');
        out.flush();
        out.writeUTF(id);
        out.flush();        
        updateTable();
    }
    
    public static void saveTaskLog() throws IOException{
       out.writeChar('W');
       out.flush();
    }
    
}
