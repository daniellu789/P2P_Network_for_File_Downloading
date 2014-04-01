import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

class FileSplit {
    public static void splitFile(File f) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(f));
        FileOutputStream out;
        String name = f.getName();
        int partCounter = 0;
        long totalSize = f.length();
        int eachsize = (int) totalSize / 15; //split the file into 15 pieces
        System.out.println("File size is "+totalSize);
        System.out.println("Each chunk size is "+eachsize);
        //System.out.println(totalSize);
        //System.out.println(eachsize);
        int sizeOfFiles = eachsize;//eachsize;// 
        byte[] buffer1 = new byte[sizeOfFiles];
        byte[] buffer2 = new byte[((int) totalSize - 14*eachsize)];
        int tmp = 0;

//********create filesize.txt
        String filesizetemp = "";
        for(int i = 1; i < 6; i++ ){
            filesizetemp = "../n"+i+"/filesize.txt";
            File filesize=new File(filesizetemp);
            if(filesize.isFile()) {
                filesize.delete();}
            filesize.createNewFile();

           FileWriter fw = new FileWriter(filesize, true);
           BufferedWriter bw = new BufferedWriter(fw);
           bw.write(sizeOfFiles+"\n");
           bw.flush();
           bw.write(((int) totalSize - 14*eachsize)+"\n");
           bw.flush();
           bw.close();
           fw.close();
        }
//********create filename.txt and initialfile.txt
        for(int i = 1; i < 6; i ++){
            File filename=new File("../n"+i+"/filename.txt");
            if(filename.isFile()) {
                filename.delete();}
             filename.createNewFile();

            File initialfile=new File("../n"+i+"/initialfile.txt");
            if(initialfile.isFile()) {
                initialfile.delete();}
             initialfile.createNewFile();

                FileWriter fw2 = new FileWriter(filename, true);
                BufferedWriter bw2 = new BufferedWriter(fw2);

            for(int j = 0; j < 15; j ++){
                String splitFileName = name+"."+String.format("%03d", j);               
               bw2.write(splitFileName+"\n");
               bw2.flush();
            }
            
                FileWriter fw3 = new FileWriter(initialfile, true);
                BufferedWriter bw3 = new BufferedWriter(fw3);
            for(int j = (i - 1)*3; j < (i - 1)*3 +3; j++){
               String initialfileName = name+"."+String.format("%03d", j);               
               bw3.write(initialfileName+"\n");
               bw3.flush();
            }
                bw2.close();
                fw2.close();
                bw3.close();
                fw3.close();
        }

       // String splitFileName = "splited"+"/"+name+"."+String.format("%03d", partCounter++);

 //*****************************          
        for(int i = 0; i < 15; i++){
            if(i < 14){
               tmp = bis.read(buffer1);
            }
            else{
                tmp = bis.read(buffer2);
            }
            
            String splitFileName = "splited"+"/"+name+"."+String.format("%03d", partCounter++);

            File newFile=new File(splitFileName);
            newFile.createNewFile();
            out = new FileOutputStream(newFile);
            if(i < 14){
               out.write(buffer1,0,tmp);
            }
            else{
                out.write(buffer2,0,tmp);
            }            
            
            out.close();
        }

/*
        while ((tmp = bis.read(buffer)) > 0) {
            //f.getParent()
            File newFile=new File("Splited_File"+"/"+name+"."+String.format("%03d", partCounter++));
            newFile.createNewFile();
            out = new FileOutputStream(newFile);
            int counter = 0;
            if(counter < 14){
                out.write(buffer1,0,tmp);
            }
            
            out.close();
        }
        */
    }

    public static void main(String[] args) throws IOException {
        splitFile(new File("File/myfile.mp4"));
    }
}