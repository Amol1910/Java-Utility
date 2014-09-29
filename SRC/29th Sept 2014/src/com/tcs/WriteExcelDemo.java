package com.tcs;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import statements
public class WriteExcelDemo 
{
    public  void createExcel(HashMap mapNFllokup) 
    {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        
         
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Site Demo");          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
    //    data.put("1", new Object[] {"ID", "NAME", "LASTNAME"});
        data.put("1", new Object[] {1,"SITE Name                  ", "NFLookup Status               "});
        
        Iterator iterator = mapNFllokup.entrySet().iterator();
        //Iterator iteratorping = mapPing.entrySet().iterator();
       
        int i=1;
    	while (iterator.hasNext()) {
    		Map.Entry mapEntry = (Map.Entry) iterator.next();
    		i++;
    		//Map.Entry mapEntryPing = (Map.Entry) iteratorping.next();
    		//System.out.println( mapEntry.getKey() +" ------------------------ "+ mapEntry.getValue() + "**************************" +mapEntryPing.getValue());
    		 data.put(""+i, new Object[] {i,mapEntry.getKey(), mapEntry.getValue()});
    	}
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
        	File file =new File("E://Site Data Status//Site Data Demo.xlsx");
    		if(file.exists())
    		file.delete();
            FileOutputStream out = new FileOutputStream(new File("Site Data Demo.xlsx"));
            workbook.write(out);
            out.close();
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}