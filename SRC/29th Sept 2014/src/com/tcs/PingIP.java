package com.tcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
 
public class PingIP {
 
   public   boolean pingTest(String command) {
	  String s = new String();
	  String str=null;
	  
	  
                try {
                        Process p = Runtime.getRuntime().exec(command);
                        BufferedReader inputStream = new BufferedReader(
                                        new InputStreamReader(p.getInputStream()));
                        
                       /* while ((s = inputStream.readLine()) != null) {
                        	//System.out.println(s);
                               
                             if (s !=null) str=str+inputStream.readLine();
                                
                            
                        }*/
                        StringBuffer buffer = new StringBuffer();
                        String line = null;
                        while ( (line = inputStream.readLine()) != null) {
                            buffer.append(line).append("\n");
                        }
                        str = buffer.toString();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();  
                    }
                   
                      
 
                
                        
                        catch (Exception e) {
                        e.printStackTrace();
                }
                
               //System.out.println("Ping Result :"+str);
             boolean flag=strCheck(str);
           
             
             
				return flag;
        }
   
   
   
	   public  boolean strCheck(String strChk) {
	      boolean flag=false;
	      int intIndex = strChk.indexOf("100% loss");
	      if(intIndex == - 1){
	         System.out.println("Ping Successful" +intIndex);
	         flag=true;
	      }else{
	         System.out.println("Ping Failure " + intIndex);
	         
	      }
	      return flag;
	   }
	
 
        public static void main(String[] args) {
        	PingIP pingip=new PingIP();
               String ip = "childrens.advil.com";
            
        }
}