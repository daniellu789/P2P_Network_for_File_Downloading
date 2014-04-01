
import java.io.*;
import java.io.FileInputStream;
 
public class merge extends Thread {

    merge(){
        start();
    }

    private static void copyfile(String[] filearray){
        try{
            File f[];
            f = new File[15];
            for(int i = 0; i < 15; i++){
                 f[i] = new File("splited/"+filearray[i]);
            }
           
            String [] fname = null;
            readfilename rfn = new readfilename();
             fname = rfn.readfilename("filename.txt");

             String temp = fname[0];
             int needlength = temp.length() - 4;
             String oriName = temp.substring(0, needlength);

            File fout = new File("dest/"+oriName);
            InputStream in[];
            in = new InputStream[15];
            for(int i = 0; i < 15; i++){
                in[i] = new FileInputStream(f[i]);
            }
 
            OutputStream out = new FileOutputStream(fout,true);
 
            byte[] buf = new byte[8192];
            int len;

            for(int i = 0; i < 15; i++){
                while ((len = in[i].read(buf)) > 0){
                out.write(buf, 0, len);
                }
                in[i].close();
            }

            out.close();
            System.out.println("File merged.");
        }
        catch(FileNotFoundException ex){
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        }
        catch(IOException e){
            System.out.println(e.getMessage());            
        }
    }

    public void run() {

        String [] filearray = null;
        readfilename fn = new readfilename();
         filearray = fn.readfilename("filename.txt");

        int counter = 0;
        int flag = -1;

        int [] filesizes = null;
        readfilesize fs = new readfilesize();
        filesizes = fs.readport();
        int small = filesizes[0];
        int big = filesizes[1];

        while(true){
            counter = 0;
            for(int i = 0; i < 15; i++){
                //System.out.println("b"+i);
                File f=new File("splited/"+filearray[i]);
                //System.out.println("e"+i);
                if(f.exists() ){
                    if(f.length() == small || f.length() == big){
                        counter++;
                    }
                    
                }
                if(counter == 15)
                {
                    flag = 1;
                }

            }
            if(flag == 1){
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
            //System.out.println("shouldn't be here!");
         copyfile(filearray);
         System.out.println("Congratulations, file has been transfered!");
         //System.exit(1);

    }
}
 