import java.net.*;
import java.io.*;
import java.util.*;

public class test{
 	public static void main(String args[]) throws Exception {



         //***************Client run******************
 		String [] fileName = null;
        readfilename fn = new readfilename();
         fileName = fn.readfilename();


 		for(int i = 0; i < 3; i++){

 			new transferfileClient("127.0.0.1",8000,fileName[i]);
 		}
        
        //*******************************Server 
 
    }
    }