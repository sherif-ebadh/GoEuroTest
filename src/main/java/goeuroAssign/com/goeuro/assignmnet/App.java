package goeuroAssign.com.goeuro.assignmnet;

import org.json.JSONArray;

import goeuroAssign.com.goeuro.assignmnet.util.ServiceUtility;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String filePath = "c:\\test.csv" ;
    	if(args.length <= 0){
    		System.out.println("CityName must be passed as Argument");
       }else{
    	   String result = ServiceUtility.callServiceAPI(args[0]);
    	   if(result != null){
    		   JSONArray infoArray  = ServiceUtility.prepareJSONArray(result);
    		   if(ServiceUtility.createCSVFile(infoArray , filePath)){
        		   System.out.println("File Created succesfully on the following path "+filePath);
        	   }
    	   }
    	   
       }
    	
        
    }
}
