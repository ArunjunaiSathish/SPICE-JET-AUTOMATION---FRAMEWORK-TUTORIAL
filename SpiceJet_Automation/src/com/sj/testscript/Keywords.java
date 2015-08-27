package com.sj.testscript;

public class Keywords extends Driverscript {
	public static String Objvalue;
	
	public static String Navigate(String objname,String data){
		if(objname.equals("testsiteurl")){
			Objvalue = CONFIG.getProperty("testsiteurl");
			return Spicejetpage.navigate(Objvalue,data);
		}
		else{
			Objvalue = OBJECTS.getProperty(objname);	
			return Functionlibrary.navigate(Objvalue,data);
		}
		
	}

}
