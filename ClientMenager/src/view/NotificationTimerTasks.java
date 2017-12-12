/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class NotificationTimerTasks extends TimerTask {
    
    private static int num;
    private static String name;
    private static String time;
    private static String descr;
    private static String cont;
    
    public NotificationTimerTasks(int nu, String n, String t, String d, String c){
        num=nu;
        name = n;
        time = t;
        descr = d;
        cont = c;
    }

    @Override
    public void run() {
        SimpleNotification[] frames = new SimpleNotification[num];
        for (int i = 0; i < num; i++) {
            frames[i] = new SimpleNotification(i, name, time, descr, cont);
            frames[i].setResizable(false);
            frames[i].pack();
            frames[i].setLocationRelativeTo(null);
            frames[i].setVisible(true);
            frames[i].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

}
