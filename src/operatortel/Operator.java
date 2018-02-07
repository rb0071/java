package operatortel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Operator {
	private Time time;
	private static ArrayList<Data> arrayData;

	public static void main(String[] args) throws ParseException, JSONException, IOException {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		String path = "promotion1[1580].log";
		File fileRead = new File(path);
		arrayData = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileRead));
			String line;
			String[] splited = null;
			
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				splited = line.split("\\|");
				String dateUse = null;
				String promotion = null;
				String telephone = null;
				float priceSum = 0;
				Data data = new Data();
				try {
					java.util.Date startTime = timeFormat.parse(splited[1]);
					java.util.Date endTime = timeFormat.parse(splited[2]);
					long difference = endTime.getTime() - startTime.getTime();
					//System.out.println("difference... "+difference/1000);
					if ((difference/1000)/60 > 60) {
						System.out.println("difference... "+df.format((float)(difference/1000)/60));
						dateUse = splited[0];
						telephone = splited[3];
						priceSum = (float)(difference/1000)/60;
						promotion = splited[4];
					}else {
						long minute = (difference/1000);
						System.out.println(splited[3]+" Secound "+minute+" 3 บาท");
						dateUse = splited[0];
						telephone = splited[3];
						priceSum = 3.00f;
						promotion = splited[4];
					}
					
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				data.setDate(dateUse);
				data.setPromotion(promotion);
				data.setTel(telephone);
				data.setPrice(priceSum);
				arrayData.add(data);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONArray array = new JSONArray();
		for (int i = 0; i < arrayData.size(); i++) {
			array.put(new JSONObject()
					.put("Date", arrayData.get(i).getDate())
					.put("Promotion", arrayData.get(i).getPromotion())
			        .put("telephone_number", arrayData.get(i).getTel())
			        .put("price", df.format(arrayData.get(i).getprice())));
	         //System.out.println("Array : "+arrayData.get(i).getTel());
	    }
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("customer", array);
		
		System.out.println(new JSONObject()
		        .put("customer", array)
		        .toString());

		// try-with-resources statement based on post comment below :)
		try (FileWriter fileWrite = new FileWriter("D:/file1.json")) {
			fileWrite.write(jsonObject.toString());
			System.out.println("Successfully Copied JSON Object to File...");
			//System.out.println("\nJSON Object: " + obj);
		}
	}
	

}
