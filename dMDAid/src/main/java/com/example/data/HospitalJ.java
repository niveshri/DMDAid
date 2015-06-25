package com.example.data;

import android.R.integer;

public class HospitalJ {
	
	public Integer _id; 
	public String Day; 
	 
	public String Reason;
	
	public String NameHosp;
	public String NamePhy;
	public String Vital;
	public String Recommendation;
	public String Nurses;
	public String Date;
	public String Careplan;
	
	
	
	
	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getdayString() {
		return Day;
	}
	public void setdayString(String DayString) {
		this.Day =DayString;
	}
	public String getreasonString() {
		return Reason;
	}
	public void setreason(String Reason) {
		this.Reason = Reason;
	}
	
	public String getnamehospString() {
		return NameHosp;
	}
	public void setNameHosp(String namehosp) {
		this.NameHosp = NameHosp;
	}
	public String getnamephyString() {
		return NamePhy;
	}
	public void setNamePhy(String namephy) {
		this.NamePhy = NamePhy;
	}
	public String getvitalString() {
		return Vital;
	}
	public void setVital(String vital) {
		this.Vital = Vital;
	}
	
	public String getrecommendationString() {
		return Recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.Recommendation = Recommendation;
	}
	public String getnursesString() {
		return Nurses;
	}
	public void setNurses(String nurses) {
		this.Nurses = Nurses;
	}
	public String getdateString() {
		return Date;
	}
	public void setDate(String date) {
		this.Date =Date;
	}
	public String getcareplanString() {
		return Careplan;
	}
	public void setCareplan(String careplan) {
		this.Careplan = Careplan;
	}
	
	@Override
	public String toString() {
		return Day + " "
				+ Reason +" "
				+ NameHosp +" "
				+ NamePhy +" "
				+ Vital +" "
				+ Recommendation +" "
				+ Nurses +" "
				+Date +" "
				+Careplan +" ";

	}
}
