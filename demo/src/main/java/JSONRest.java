import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.tomcat.util.buf.StringUtils;
import org.json.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.io.FileWriter;

//import com.google.gson.Gson;
//import java.io.IOException;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import java.nio.file.*;
//import java.util.*;
//import java.lang.*;

public class JSONRest {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      //System.out.println(jsonText);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  

  public static void main(String[] args) throws IOException, JSONException {
    
	String dateInfo = "2017-07-13";
	String head = "http://192.168.3.240:9200/_search?from=";
	int fromInfo = 0;
	String mid = "&size=10&index=collector.logs-";
	String tail = "&sort=reqNo";
	
	
	JSONObject json = readJsonFromUrl(head + fromInfo + mid + dateInfo + tail);

    JSONObject deneme = json.getJSONObject("hits");

    
    int total = deneme.getInt("total");
   
    
    	for(int a=0; a<((total/10)+1); a++) {
    		fromInfo = (a*10);
    		JSONObject json1 = readJsonFromUrl(head + fromInfo + mid + dateInfo + tail);
        
    		JSONObject deneme1 = json1.getJSONObject("hits");
        
    		for(int x=0; x<10; x++) {
    			org.json.JSONArray jsonArray = deneme1.getJSONArray("hits");
    			JSONObject jsonArrayElement = jsonArray.getJSONObject(x);
    			JSONObject jsonArrayElementSource = jsonArrayElement.getJSONObject("_source");
    			System.out.println(jsonArrayElementSource.get("content"));
    			String contentInfo = jsonArrayElementSource.getString("content") ;
            
    			try(FileWriter fileWriter = new FileWriter("C:\\Users\\pc\\Desktop\\sample.json", true);
            	    BufferedWriter bw = new BufferedWriter(fileWriter);
            	    PrintWriter out = new PrintWriter(bw))
            	{
            	    out.println("Content [" + a + "-" + x + "] = " + contentInfo);
            	    
            	} catch (IOException e) {
            	    
            	}
    		}        
    	}
    }
}

 