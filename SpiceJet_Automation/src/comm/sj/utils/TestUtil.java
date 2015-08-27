package comm.sj.utils;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import com.sj.config.Log;
import com.sj.testscript.Driverscript;

public class TestUtil extends Driverscript{


	public static String now(String dateformat) {
		
		   Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
	        return sdf.format(cal.getTime());

	}

	public static void takeScreenShot(String path) {
		
    	File scrFile = null;
    	
        try 
        {
        	
        	// Take Screenshot from local machine
        	
        		
        	
        		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        		
        	// Store screenshot to the path provided
        	FileUtils.copyFile(scrFile, new File(path));

        }
        
        catch (Throwable screenCaptureException) 
        {
            // Log error
            log.debug("Error while taking screenshot : " +screenCaptureException.getMessage());
            System.err.println("Error while taking screenshot : " +screenCaptureException.getMessage());
            
        }

		
	}
	

}
