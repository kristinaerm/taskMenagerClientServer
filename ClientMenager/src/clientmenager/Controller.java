/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmenager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Controller {
    
    public static DataInputStream in;
    public static DataOutputStream out;
    public static DefaultTableModel model;
    public static Record[] records;
    
    public static void updateTable(){
        
    }
    
    public static int getSize(){
        return 0;
    }
    
    public static Record[] getRecords(){
        records = new Record[0];
        return records;
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
