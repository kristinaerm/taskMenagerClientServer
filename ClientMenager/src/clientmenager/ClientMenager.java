/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmenager;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import view.SimpleTaskManager;

/**
 *
 * @author Кристина
 */
public class ClientMenager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int serverPort = 1024;
        String address = "localhost";
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {

            socket = new Socket(address, serverPort);
            System.out.println("Соединение с сервером установлено");

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Controller.in = in;
            Controller.out = out;
            
            SimpleTaskManager frame = new SimpleTaskManager();
            frame.setResizable(false);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTray();

            frame.addWindowListener(new WindowListener() {
                @Override
                public void windowClosing(WindowEvent event) {

                    // [ТУТ  ДЕЙСТВИЯ ПО ЗАКРЫТИЮ]
                    //****************************
                    System.exit(0);
                }

                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {
                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });

            
        } catch (IOException e) {
            System.err.println(e);
        }

        try {
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientMenager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientMenager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientMenager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientMenager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
