package com.sj.datatable;




import java.io.*;

import jxl.*;
import jxl.biff.*;
import jxl.read.biff.BiffException;
public class XlsReader {

	public FileInputStream fs;
	public String filepath;
	//TO read input file
	public XlsReader(String filepath) 
	{
		System.out.println(filepath);
		this.filepath =filepath;
		try {
			fs = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	public String getFirstsheetname() throws BiffException, IOException {
		File f = new File(filepath);
		Workbook w;
		w = Workbook.getWorkbook(f);
		String sheetname = w.getSheet(0).getName();
		return sheetname;
	}
	public int getRowcount(String firstsheetname) throws BiffException, IOException {
		File f = new File(filepath);
		Workbook w;
		w = Workbook.getWorkbook(f);
		Sheet s = w.getSheet(firstsheetname);
		int rowcount=0;
		for(int i=0;i<s.getRows();rowcount++){
			rowcount++;
		}
		
		return rowcount;
	}
	public String getCelldata(String Sheetname, String colName, int rowNum) throws BiffException, IOException {
		File f = new File(filepath);
		Workbook w;
		w = Workbook.getWorkbook(f);
		Sheet s = w.getSheet(Sheetname);
		int i=0;
		int colNum;
		for(i=0; i<s.getColumns(); i++)
		{	
			if(s.getCell(i, 0).getContents().equals(colName))
			{
				break;
			}
		}

		colNum=i;

		Cell cell = s.getCell(colNum, rowNum);
		return cell.getContents();
	
	}
	public String getCellData(String sheetName, String colName) throws BiffException, IOException
	{
		File inputWorkbook = new File(filepath);
		Workbook w;
		w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(sheetName);
		int i=0;
		int colNum; 
		int rowNum=1;
		for(i=0; i<sheet.getColumns(); i++)
		{	
			if(sheet.getCell(i, 0).getContents().equals(colName))
			{
				break;
			}
		}

		colNum=i;

		Cell cell = sheet.getCell(colNum, rowNum);
		return cell.getContents();
	}
	

}
