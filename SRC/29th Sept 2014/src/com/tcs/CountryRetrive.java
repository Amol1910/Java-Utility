package com.tcs;

import java.text.SimpleDateFormat;
import java.util.Locale;
public class CountryRetrive {
  
    public static void main(String[] args) {
         String str="aliviatudolor.co";
         String strNew=str.substring(str.lastIndexOf('.')+1, str.length());
         System.out.println(strNew.toUpperCase());
        //returns array of all locales
        Locale locales[] = SimpleDateFormat.getAvailableLocales();
         
        //iterate through each locale and print 
        // locale code, display name and country
        for (int i = 0; i < locales.length; i++) {
         
            System.out.println( locales[i].getCountry()+":"+locales[i].getDisplayCountry());
                //locales[i].getDisplayName(), locales[i].getDisplayCountry());
         
        }
    }
}