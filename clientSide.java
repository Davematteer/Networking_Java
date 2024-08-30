// this is the client side code 

import java.io.*;

import java.net.*;

public class clientSide{
  
  private Socket socket = null;
  private DataInputStream input = null;
  private DataOutputStream output = null;
  private BufferedReader reader = null;

  public clientSide(String address, int port){
    
    // This is for trying to establish a connection
    try {
      socket = new Socket(address, port);
      
      System.out.println("Connecting ...");
      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException e){
        System.out.println(e);
      }
      System.out.println("Connected");
      
      // Here we take input from the terminal 

      input = new DataInputStream(System.in);
      reader = new BufferedReader(new InputStreamReader(input));
      
      // sends the output to the socket
      output = new DataOutputStream(socket.getOutputStream());

    }

    catch (UnknownHostException u){

      System.out.println(u);
    }
    
    catch (IOException i) {
      System.out.println(i);
    }
    
    // Reading the message from input 
    String line = "";

    //keep reading until "End" is input
    while (!line.equals("End")){
      try {
        line = reader.readLine();
        output.writeUTF(line);
      }
      catch (IOException i){
        System.out.println(i);
      } 
      }
      // closing the connection 
      try{
        input.close();
        output.close();
        socket.close();
      }
      catch (IOException i){
        System.out.println(i);
      }
    
  }
  public static void main(String[] args)
    {
      clientSide client = new clientSide("localhost",6000);
    }

}
