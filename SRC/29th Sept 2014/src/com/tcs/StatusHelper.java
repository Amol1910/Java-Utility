package com.tcs;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/*
* Main.java
*

*/
public class StatusHelper {
	
	static List  lsites = getData();
    /*
     * This method performs a NS Lookup
     */
    public HashMap performNSLookup() {
        
    	
    	
        	
        	LinkedHashMap map=new LinkedHashMap();
          
            
            for (int i=0;i<lsites.size();i++){
            	  try {
            	
            	  InetAddress host = InetAddress.getByName(lsites.get(i).toString());
            	 
                
                System.out.println("hostAdd : " + host);
                  map.put(lsites.get(i).toString(), "NSLookup Successful ,LIVE ,"+host.getHostAddress()+","+getRegion(host.getHostAddress())+","+getCountryforSite(lsites.get(i).toString()));
                  
            }
          
            
         catch(UnknownHostException ex) {
        	 map.put(lsites.get(i).toString(), "NSLookup Failure,NOT LIVE");
            System.out.println("Unrecognized host==="+ex.getCause() );
         //  ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    }
            return map;
    }
	public static List getData() {
		File inputFile = new File("E://Java Utility//Amol.xlsx");
		ExcelReader excel=new ExcelReader();
		System.out.println ("CHeck thios");
		List lsites=excel.readXlsx(inputFile);		
		return lsites;
	}
	
	public String getCountryforSite(String str)
	{
		
        String strNew=str.substring(str.lastIndexOf('.')+1, str.length());
        System.out.println(strNew.toUpperCase());
        String strRegion="NO Country Found";
       //returns array of all locales
       Locale locales[] = SimpleDateFormat.getAvailableLocales();
        
       //iterate through each locale and print 
       // locale code, display name and country
       for (int i = 0; i < locales.length; i++) {
        if(strNew.equalsIgnoreCase(locales[i].getCountry()))
        {
        	strRegion=locales[i].getDisplayCountry();
           //System.out.println( locales[i].getCountry()+":"+locales[i].getDisplayCountry());
               //locales[i].getDisplayName(), locales[i].getDisplayCountry());
        
       }
        else if(strNew.equalsIgnoreCase("sr"))
        {
        	strRegion="Suriname";        
        }
        
        else if(strNew.equalsIgnoreCase("com"))
        {
        	strRegion="USA";           
        
       }
        else if(strNew.equalsIgnoreCase("uk") ||  strNew.equalsIgnoreCase("eu"))
        {
        	strRegion="UK";           
        
       }
        else if(strNew.equalsIgnoreCase("pk"))
        {
        	strRegion="Pakistan";           
        
       }
        
   }
       System.out.println(strRegion);
       return strRegion;
	}
	public String getRegion(String strIP){
		String str="No Match Found for IP";
		if(strIP.equalsIgnoreCase("184.73.156.141") || strIP.equalsIgnoreCase("54.204.47.116"))
		{
			str="USA";
			
		}
		
		else if(strIP.equalsIgnoreCase("54.247.122.4") || strIP.equalsIgnoreCase("54.247.72.10"))
		{
			str="GARDEN";
			
		}
		else if(strIP.equalsIgnoreCase("54.254.106.208"))
		{
			str="APAC";
			
		}
		
		else if(strIP.equalsIgnoreCase("54.254.106.208"))
		{
			str="APAC";
			
		}
		
		else if(strIP.equalsIgnoreCase("79.125.106.190"))
		{
			str="EMEA";
			
		}
		
		else if ( strIP.startsWith("148.168") )
		{
			str=".NET";
		}
		
		System.out.println("*****"+str);
		return str;
	}
	

    /*
     * @param args the command line arguments
     */
    public  void writeandSendEmail() {
    	long start = System.currentTimeMillis(); 
        HashMap map=performNSLookup();
        LinkedHashMap mapPing=new LinkedHashMap();
        String str=null;
    //    List lstping = getData();  /// CHANGE THIS IF Error
        
      	/*PingIP pingip=new PingIP();
      	boolean flagChk=false;
      	 for (int i=0;i<lsites.size();i++){
      		flagChk=pingip.pingTest("ping " +lsites.get(i).toString());
      		//System.out.println("str from Test Class" + str);
      		if ( flagChk )
      			mapPing.put(lsites.get(i).toString(), "Ping Successful");
      		else
      			mapPing.put(lsites.get(i).toString(), "Ping Failure");
      	 }
        */
      //  System.out.println(map);
      // System.out.println(mapPing);
        
        Iterator iterator = map.entrySet().iterator();
        Iterator iteratorping = mapPing.entrySet().iterator();
     //   System.out.println("Site Nam e      ------------------------            NSLookup Status  ********************** Ping Status");
        
        
    	/*while (iterator.hasNext()) {
    		Map.Entry mapEntry = (Map.Entry) iterator.next();
    		Map.Entry mapEntryPing = (Map.Entry) iteratorping.next();
    		System.out.println( mapEntry.getKey() +" ------------------------ "+ mapEntry.getValue() + "**************************" +mapEntryPing.getValue());
    		
    	}*/
    	WriteExcelDemo demo =new WriteExcelDemo();
    	demo.createExcel(map);
    	SendAttachmentInEmail sendMail =new SendAttachmentInEmail();
    	
    	sendMail.sendMail();
    	
    	long end = System.currentTimeMillis();  
        
    	System.out.println("processing Time in seconds: " + ((end - start) / 1000));  
 
    	/*System.out.println(""); System.out.println(""); System.out.println("");
    	 
         System.out.println("Site Name             ------------------------     Ping Status");
         System.out.println(""); System.out.println(""); System.out.println("");
         
     	while (iteratorping.hasNext()) {
     		Map.Entry mapEntry = (Map.Entry) iteratorping.next();
     		System.out.println( mapEntry.getKey() +" ------------------------ "+ mapEntry.getValue());
     	}
        	*/
    }
}