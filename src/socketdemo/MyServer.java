
package socketdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author maze
 */
public class MyServer {

    static String hostName ="localhost";
    static int port = 8888;
    
    public static void main(String[] args) {
        
        try {
            if(args.length ==2)
            {
                hostName = args[0];
                port = Integer.parseInt(args[1]);
            }
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(hostName,port));
            Logger.getLogger(MyServer.class.getName()).log(Level.INFO,"Server Started, listening on port: "+port);
            while(true)
            {
                Socket socket = serverSocket.accept();
                handleClient(socket);
                System.out.println("XXXXXXXXXXXXXXXXXX");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void handleClient(Socket socket) {
        
        try{
            Scanner scan = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            String result = scan.nextLine();  //Blocking call
            while(!result.equals("#STOP#"))
            {
                result = result.toUpperCase();
                pw.println(result);
                result = scan.nextLine();
            }
            socket.close();
            
        }catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
}
