// This is the serverside code 

import java.io.*; // for reading data streams

import java.net.*; // for network classes 

public class serverSide{

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public serverSide(int port){
      
      try {
        server = new ServerSocket(port);
        System.out.println("Server is ready for connection");
        System.out.println("listening on port "+port+" and Waiting for client ...");
        
        // this accepts the request from 
        // the client and creates a new socket
        socket = server.accept();
        
        // takes the stream data from the socket
        in = new DataInputStream(
            new BufferedInputStream(socket.getInputStream()));
        String line = "";

        while (!line.equals("End")){

          try {
            
            line = in.readUTF();

            System.out.println(line);
          }

          catch (IOException i){
            
            System.out.println("Closing connection");
          }
        }

        System.out.println("Closing the connection ...");

        socket.close();
      }

      catch (IOException i) {
        System.out.println(i);
      }

    }

    public static void main(String[] args){

      serverSide server = new serverSide(6000);
    }
} 


