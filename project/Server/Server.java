// FTP Server

import java.net.*;
import java.io.*;
import java.util.*;

public class Server
{
    public static void main(String args[]) throws Exception
    {
        //****************split******************
        BufferedReader br;
        br=new BufferedReader(new InputStreamReader(System.in));
        String filename;
        System.out.print("Enter File Name :");
        filename=br.readLine();

        FileSplit fp = new FileSplit();
        fp.splitFile(new File("File/"+filename));



        //*******************************Server 
        
        ServerSocket soc=new ServerSocket(8000);
        System.out.println("FTP Server Started on Port Number " + 8000);
        while(true)
        {
            //System.out.println("Waiting for Connection ...");
            transferfile t=new transferfile(soc.accept()); 
            //System.out.println("HELLO~~~~~~~~");
        }
    }
}

class transferfile extends Thread
{
    Socket ClientSoc;

    DataInputStream din;
    DataOutputStream dout;
    
    transferfile(Socket soc)
    {
        try
        {
            ClientSoc=soc;                        
            din=new DataInputStream(ClientSoc.getInputStream());
            dout=new DataOutputStream(ClientSoc.getOutputStream());
            //System.out.println("FTP Client Connected ...");
            start();
            
        }
        catch(Exception ex)
        {
        }  
        //start();      
    }
    void SendFile() throws Exception
    {        
        String filename=din.readUTF();
        File f=new File(filename);
        //System.out.println(f.length());
        while(true){
            f = new File(filename);
            if(!f.exists())
            {
                System.out.println("finding..."+filename);//dout.writeUTF("File Not Found");
                Thread.sleep(2000);
            }

             if(f.exists()  ){
                break;
             }
        }




            dout.writeUTF("READY");
            FileInputStream fin=new FileInputStream(f);
            int ch;
            do
            {
                ch=fin.read();
                dout.writeUTF(String.valueOf(ch));
            }
            while(ch!=-1);    
            fin.close();    
            dout.writeUTF("File Receive Successfully");                            

    }
    

    public void run()
    {
       // while(true)
        //{
            try
            {

                SendFile();
                //continue;
            }
            catch(Exception ex)
            {
            }
        //}
    }
}