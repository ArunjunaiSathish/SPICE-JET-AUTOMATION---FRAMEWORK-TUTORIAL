package com.sj.testscript;

public class Functionlibrary extends Driverscript{

	public static String navigate(String objvalue, String data) {
		driver.get(objvalue);
		if(!driver.getTitle().equals("")){
			return "Pass";
		}
		else{
			return "Fail";	
		}
		
	}

	

}
