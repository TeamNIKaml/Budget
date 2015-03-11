package com.teamNikAml.budget.model;


public class Constants {
	
	public static final String DB_NAME = "budget.db";
	
	public static final String INCOME_STATEMENT_TABLE_NAME = "incomeStatement";
	public static final String INCOME_STATEMENT_DB_QUERY = "CREATE TABLE IF NOT EXISTS "
			+ Constants.INCOME_STATEMENT_TABLE_NAME
			+ "("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
			+ "user TEXT,"
			+ "catagory TEXT,"
			+ "type TEXT,"
			+ "amount TEXT,"
			+ "date TEXT)";
	
	private Constants() {
	}


}
