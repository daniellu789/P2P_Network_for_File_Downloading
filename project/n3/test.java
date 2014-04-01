import java.net.*;
import java.io.*;
import java.util.*;

public class test{
 	public static void main(String args[]) throws Exception {



         //***************Client run******************
        int [] filesizes = null;
        readfilesize fs = new readfilesize();
        filesizes = fs.readport();
        int small = filesizes[0];
        int big = filesizes[1];

            System.out.println(small);
            System.out.println(big);
        //*******************************Server 
 
    }
    }