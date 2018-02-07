package operatortel;

import java.sql.Date;

public class Data {
	private String tel;
	private float price;
	private String date;
	private String promotion;
	
	public String getTel(){
	     //include validation, logic, logging or whatever you like here
	    return this.tel;
	}
	
	public float getprice(){
	     //include validation, logic, logging or whatever you like here
	    return this.price;
	}
	
	public void setTel(String tel){
	     //include more logic
	     this.tel = tel;
	}
	
	public void setPrice(float price){
	     //include more logic
	     this.price = price;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public float getPrice() {
		return price;
	}
	
	
}
