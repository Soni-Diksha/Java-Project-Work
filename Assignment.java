import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;

public class Assignment {
	Object object = null;
	JSONObject jo = null;
	JSONArray temp_list = null;
	JSONObject list_obj = null;
	JSONObject main = null;
	JSONArray weather = null;
	JSONObject weather_data = null;
	List<Double> mintemp_list = new ArrayList<>();
    List<Double> maxtemp_list = new ArrayList();
    Set<Double> humidity = new HashSet<>();
    Set main_list = new HashSet();
    Set rain = new HashSet();
    
    public void getList() throws Exception {
    	object = new JSONParser().parse(new FileReader("D:\\My Learnings\\core java training\\hourly.json"));   
        //Typecasting object to JSONObject 
        jo = (JSONObject) object;
        temp_list = (JSONArray) jo.get("list");
    }
    
    public void findMaxTemp() throws Exception {
    	double max;
    	List<Double> max_temp = new ArrayList<>();
    	for(int i = 0; i < temp_list.size(); i++ ) {
        	list_obj = (JSONObject) temp_list.get(i);
        	main = (JSONObject) list_obj.get("main");
        	Number temp_max = (Number) main.get("temp_max");
        	max_temp.add(temp_max.doubleValue());
    	} 	
    	max = (double) Collections.max(max_temp);	
    	System.out.println("maximum temp of the List is : " + max);
	}
    
	public void givenDateDetails(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the date in the format of yyyy-mm-dd : "); 
		String user_date = sc.nextLine();
		
		for(int i = 0; i < temp_list.size(); i++ ) {
        	list_obj = (JSONObject) temp_list.get(i);
        	main = (JSONObject) list_obj.get("main");
        	weather = (JSONArray) list_obj.get("weather");
        	weather_data = (JSONObject) weather.get(0);
        	
        	//specified date data
        	String[] date = ((String) list_obj.get("dt_txt")).split(" ");
			
			if(user_date.equals(date[0])) { 
				Number humid,minimum,maximum;
				humid = (Number) main.get("humidity");
				minimum = (Number) main.get("temp_min");
				maximum = (Number) main.get("temp_max");
				
				humidity.add(humid.doubleValue());
				mintemp_list.add(minimum.doubleValue());
				maxtemp_list.add(maximum.doubleValue());
				main_list.add(weather_data.get("main"));	
			}
		}
		
		double minimum_temp = Collections.min(mintemp_list);
        System.out.println("min temp of specified date is : " + minimum_temp);
        
        double maximum_temp = Collections.max(maxtemp_list);
        System.out.println("max temp of specified date is : " + maximum_temp );
        
        System.out.println("humidity of specified date is : " + humidity);
        System.out.println("main under weather of specified date is : " + main_list);
	}
	
	public void rainChecking() throws Exception {
		for(int i = 0; i < temp_list.size(); i++ ) {
        	list_obj = (JSONObject) temp_list.get(i);
        	weather = (JSONArray) list_obj.get("weather");
        	weather_data = (JSONObject) weather.get(0);
        	String[] date = ((String) list_obj.get("dt_txt")).split(" ");
			String rainCheck = (String) weather_data.get("main");
			if(rainCheck.equals("Rain"))
				rain.add(date[0]);
		}
		System.out.println("the list of the dates when it will rain : " + rain);
	}
	
	public static void main(String[] args) throws Exception {
		Assignment obj = new Assignment();
		obj.getList();
		obj.rainChecking();
		obj.findMaxTemp();
		while("True" != null) {
			try {
				obj.givenDateDetails();
				break;
			}catch(Exception ex) {
				System.out.println("Data not found on this date, please enter another date");
			}
		}

	}

}
