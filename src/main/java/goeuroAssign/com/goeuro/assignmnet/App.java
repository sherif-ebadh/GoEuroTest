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
    	String filePath = "output.csv" ;
    	if(args.length <= 0){
    		System.out.println("CityName must be passed as Argument");
       }else{
    	  if(args.length == 2 && args[1] != null){
    		  // this to change the output path file - otional parameter -
    		  filePath = args[1];     		  
    	  }
    	  if(args[0] != null){
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
}
