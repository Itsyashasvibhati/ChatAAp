import java.io.*;
import java.net.*;


 class Server {


ServerSocket server;
Socket socket;

BufferedReader br;
PrintWriter out;


// constructor

public Server() {
    try {
         server = new ServerSocket(7777);
         System.out.println("server is ready to accept connection"); 
         System.out.println("waiting...");
         socket = server.accept();  

                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 out = new PrintWriter( socket.getOutputStream()); 

                 startReading();
                 startWriting();

      } catch(Exception e) {

        e.printStackTrace();
    }


}


  public void startReading()
  {
    // thread-readkarke deta rahega

    Runnable r1=() -> {

       System.out.println("reader started..");
   try {
    while(true)
    {  
     

        String msg=br.readLine();
        if(msg.equals("exit"))
        {
            System.out.println("Client terminated the chat");
            break;
            
      }

      System.out.println("Client  : "+msg);



    }
    
    } catch (Exception e) {
        e.printStackTrace();
    }


 
    };

    new Thread(r1).start();

  }


  public void startWriting() {
      // thread = data user lega and the send  karega client tak 
      Runnable r2=() -> {
        System.out.println("writer started..");
        try{
        while (true) {
             
                 
                 BufferedReader br1 = new BufferedReader(new
                 InputStreamReader(System.in));
                  String content=br1.readLine();
                  out.println(content);
                  out.flush();

                  if (content.equals("exit")) {
                    socket.close();
                    break;
                  }

           
        }
         } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();    
             }
      

     };

     new Thread(r2).start();


  }
  






    public static void main(String[] args) {
        System.out.println("this is server.. going to start server");
        new Server();  // call kiya hai
    }
}
  
