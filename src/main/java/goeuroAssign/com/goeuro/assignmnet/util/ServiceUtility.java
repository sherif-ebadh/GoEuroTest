package goeuroAssign.com.goeuro.assignmnet.util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class ServiceUtility {
	public static boolean createCSVFile(JSONArray jsonArray, String filePath) {
		try {
			File file = new File(filePath);
			String csv = CDL.toString(jsonArray);
			FileUtils.writeStringToFile(file, csv);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static JSONArray prepareJSONArray(String jsonString) {
		JSONArray jArray = new JSONArray();

		try {
			JSONArray infoArray = new JSONArray(jsonString);
			for (int i = 0; i < infoArray.length(); i++) {
				LinkedHashMap<String, String>  valueMap = new LinkedHashMap<String, String>();
				JSONObject obj = infoArray.getJSONObject(i);
				valueMap.put("_id", obj.getString("_id"));
				valueMap.put("name", obj.getString("name"));
				valueMap.put("type", obj.getString("type"));
				
				JSONObject positionObject = obj.getJSONObject("geo_position");
				valueMap.put("latitude", positionObject.getString("latitude"));
				valueMap.put("longitude", positionObject.getString("longitude"));
				jArray.put(valueMap);
			}

			return jArray;
		} catch (JSONException e) {
			System.out.println("Error in parsing the JSON Array");
			e.printStackTrace();
			
			return null;
		}
	}

	public static String callServiceAPI(String cityName) {
		String url = "http://api.goeuro.com/api/v2/position/suggest/en/" + cityName;
		RestTemplate restTemp = new RestTemplate();
		String result = restTemp.getForObject(url, String.class);
		if (!result.equals("[]")) {
			return result;
		} else {
			return null;
		}

	}

}
