/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Record;
import clientmenager.Controller;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class NotificationTimerTasks extends TimerTask {

    private static int num;
    private static Record[] records;

    public NotificationTimerTasks(int nu, Record[] records) {
        num = nu;
        this.records = records;
    }

    @Override
    public void run() {
        SimpleNotification[] frames = new SimpleNotification[num];
        for (int i = 0; i < num; i++) {
            if (!Controller.list.contains(records[i].getId())) {
                Controller.list.add(records[i].getId());                        
                frames[i] = new SimpleNotification(i, records[i]);
                frames[i].setResizable(false);
                frames[i].pack();
                frames[i].setLocationRelativeTo(null);
                frames[i].setVisible(true);
                frames[i].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }
    }

}
