package com.example.data;

import android.R.integer;

public class Contact {
	
	public Integer _id; 
	public String Name; 
	public String Address; 
	public String Email;
	public String Misc_Info;
	public int Phone_number;
	public String Type;
	public int Number;
	
	
	public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getNameString() {
		return Name;
	}
	public void setNameString(String NameString) {
		this.Name = NameString;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String Misc_Info() {
		return Misc_Info;
	}
	public void setMisc_Info(String Misc_Info) {
		this.Misc_Info = Misc_Info;
	}
	public Integer getPhone_number() {
		return Phone_number;
	}
	public void setPhone_number(Integer Phone_number) {
		this.Phone_number = Phone_number;
	}
	public String getType() {
		return Type;
	}
	public void setType(String Type) {
		this.Type = Type;
	}
	public Integer getNumber() {
		return Number;
	}
	public void setNumber(Integer Number) {
		this.Number = Number;
	}
	
	
	@Override
	public String toString() {
		return Name + " "
				+ Address +" "
				+ Email +" "
				+ Misc_Info +" "
				+ Phone_number +" "
				+ Type +" "
				+ Number;

	}
}
