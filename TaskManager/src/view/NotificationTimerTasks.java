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
    
    private static int n;
    
    public NotificationTimerTasks(int num){
        n=num;
    }

    @Override
    public void run() {
        SimpleNotification[] frames = new SimpleNotification[n];
        for (int i = 0; i < n; i++) {
            frames[i] = new SimpleNotification(i);
            frames[i].setResizable(false);
            frames[i].pack();
            frames[i].setLocationRelativeTo(null);
            frames[i].setVisible(true);
            frames[i].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

}
