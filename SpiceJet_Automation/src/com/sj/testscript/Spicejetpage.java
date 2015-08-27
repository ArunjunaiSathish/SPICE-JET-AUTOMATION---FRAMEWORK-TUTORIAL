package com.sj.testscript;

public class Spicejetpage extends Driverscript{

	public static String navigate(String objvalue, String data) {
		driver.get(objvalue);
		//if screenexists
		if(!driver.getTitle().equals("")){
			return "Pass";
		}
		else{
			return "Fail";	
		}
		
	}

	
}
