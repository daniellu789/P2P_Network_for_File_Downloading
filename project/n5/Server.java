import java.net.*;
import java.io.*;
import java.util.*;

public class Server
{
    public static void main(String args[]) throws Exception
    {
        //****************Client0 thread******************
        String [] initialfile = null;
        readfilename fn1 = new readfilename();
        initialfile = fn1.readfilename("initialfile.txt");

        for(int i = 0; i < 3; i++){
            new transferfileClient("127.0.0.1",8000,initialfile[i]);
        }


         //***************Client1 thread******************
        String [] fileName = null;
        readfilename fn = new readfilename();
         fileName = fn.readfilename("filename.txt");

        int [] ports = null;
        readport rp = new readport();
        ports = rp.readport();
        int portSelf = ports[0];
        int portTarget = ports[1];

        for(int i = 0; i < 15; i++){
            new transferfileClient("127.0.0.1",portTarget,fileName[i]);
        }
        //*************merge thread*************
        new merge();


        //*******************************Server 
        
        ServerSocket soc=new ServerSocket(portSelf);
        System.out.println("My Port Number " + portSelf+" and I am linstening on "+ portTarget);
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

        int [] filesizes = null;
        readfilesize fs = new readfilesize();
        filesizes = fs.readport();
        int small = filesizes[0];
        int big = filesizes[1];

        while(true){
            f = new File(filename);
            if(!f.exists())
            {
                //System.out.println("finding..."+filename);//dout.writeUTF("File Not Found");
                Thread.sleep(2000);
            }

             if(f.exists() && (f.length() == big || f.length() == small) ){
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