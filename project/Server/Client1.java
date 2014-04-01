// FTP Client

import java.net.*;
import java.io.*;
import java.util.*;


class Client1
{
    /*
    public static void main(String args[]) throws Exception
    {
        Socket soc=new Socket("127.0.0.1",8000);
        
        //transferfileClient t=new transferfileClient(soc);
        //t.run();
        
        new transferfileClient(soc, "myfile.mp4.000");

        System.out.println("hehehehe");
        
    }
    */
}

class transferfileClient extends Thread
{
    Socket ClientSoc;
    DataInputStream din;
    DataOutputStream dout;
    BufferedReader br;
    String fileName = "";
    String ip = "";
    int port = 0;
    Socket soc = null;

    transferfileClient(String ipaddress, int portnumber,  String fileN )
    {


        ip = ipaddress;
        port = portnumber;
        fileName = fileN;
        start();      
    }

    
    void ReceiveFile(String fileN) throws Exception
    {
        
        String fileName = fileN;

            dout.writeUTF("splited/"+fileName);
            String msgFromServer=din.readUTF();

            if(msgFromServer.compareTo("READY")==0)
            {
                //System.out.println("Receiving File ..."+fileName);
                File f=new File("splited/"+fileName);
                if(f.exists())
                {
                     dout.flush();
                    return;                      
                }

                FileOutputStream fout=new FileOutputStream(f);
                int ch;
                String temp;
                do
                {
                    temp=din.readUTF();
                    ch=Integer.parseInt(temp);
                    if(ch!=-1)
                    {
                        fout.write(ch);                    
                    }
                }while(ch!=-1);
                fout.close();
                int fromNode = port - 8000;
                System.out.println("Receive File"+fileName+" from Node "+ fromNode +" successfully.");
                //System.out.println(din.readUTF());
        }

    }

    public void run()
    {

       // while(true)
        //{    
            boolean boo = true;
            while(boo){

            try{
            Thread.sleep(2000);
            soc = new Socket(ip, port);
            boo = false;
            }
            catch(Exception e)
            {
                  //System.out.println("Waitting 2 seconds to reconnect..");
                  boo = true;
            }
            //catch (InterruptedException e) {

            // }            
            }

        try {
            soc = new Socket(ip,port);
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try
        {
            ClientSoc=soc;
            din=new DataInputStream(ClientSoc.getInputStream());
            dout=new DataOutputStream(ClientSoc.getOutputStream());
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        catch(Exception ex)
        {
        }  

       // System.out.println("THis is client1");

                try {
                    ReceiveFile(fileName);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

        //}
    }
}
