
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyClient {
    
    Socket socket;
    private Scanner scan ;
    private PrintWriter pw;
    
    public void connect(String ip, int port) throws IOException
    {
        Socket s = new Socket(ip,port);
        scan = new Scanner(s.getInputStream());
        pw = new PrintWriter(s.getOutputStream());
    }
    
    public void send(String msg)
    {
        pw.print(msg);
        pw.flush();
    }
    public void stop()
    {
        pw.println("#STOP#");
    }
    public String receive()
    {
        String result = scan.nextLine(); // blocking call
        System.out.println(result);
        return "";
    }
    
    public static void main(String[] args) 
    {
        MyClient client = new MyClient();
        try {
            client.connect("localhost",9999);
            client.send("Hi class");
            client.receive();
            
            client.send("hello World");
            client.receive();
            client.stop();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MyClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
