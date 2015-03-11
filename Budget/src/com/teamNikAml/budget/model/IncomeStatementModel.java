package com.teamNikAml.budget.model;

import android.content.ContentValues;
import android.database.Cursor;



public class IncomeStatementModel {
	
	private String user,catagory,type,amount,date;
	
	private static IncomeStatementModel model ;
	
	public static IncomeStatementModel getIncomeStatementModel()
	{
		if(model == null)
			model = new IncomeStatementModel();
		return model;
	}
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	public ContentValues IncomeStatementToContentValues() {
		ContentValues values = new ContentValues();
		values.put("user", this.user);
		values.put("catagory", this.catagory);
		values.put("type", this.type);
		values.put("amount", this.amount);
		values.put("date", this.date);
		return values;
	}

	public IncomeStatementModel cursorToPasswordsDataSource(Cursor cursor) {
		
		
		IncomeStatementModel model = new IncomeStatementModel();
		
		model.setUser(cursor.getString(1));
		model.setCatagory(cursor.getString(2));
		model.setType(cursor.getString(3));
		model.setAmount(cursor.getString(4));
		model.setDate(cursor.getString(5));
		
		
		return model;
		

	}

}
