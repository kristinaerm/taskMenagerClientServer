/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmenager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.table.DefaultTableModel;

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
        out.writeChar('G');
        out.flush();
        int size = in.readInt();
        for (int i=0; i<size; i++){
            records[i]=(Record)in.readObject();
        }
        
        while (model.getRowCount() != 0) {
            for (int i = 0; i < model.getRowCount(); i++) {
                model.removeRow(i);
            }
        }
        for (int i = 0; i < records.length; i++) {
            model.addRow(new Object[]{i, records[i].getName(), records[i].getTimeString(), records[i].getDescription(), records[i].getContacts()});
        }
    }
    
    public static String addRecord(Record rec){
        
        return "OK";
    }
    
    public static String changeRecord(int number, String n, String t, String d, String c){
        return "OK";
    }
    
    public static void  deleteRecord(int number){
        
    }
    
    public static String saveTaskLog(){
       return "OK"; 
    }
    
}
