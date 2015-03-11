package com.teamNikAml.budget.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.teamNikAml.budget.model.Constants;

public class IncomeStatementHelper implements IDBHelper {

	
	private DBHelper dbHelper;
	private Context context;

	public IncomeStatementHelper(Context context)
	{
		this.context = context;
	}

	@Override
	public boolean onCreate() {
		Log.e("DBTask onCreate", "doInBackground");
		dbHelper = new DBHelper(context, 1, Constants.DB_NAME,
				Constants.INCOME_STATEMENT_DB_QUERY);
		return true;

	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		Log.e("insert Passwordhelper", "doInBackground");
		new DBTask().execute("insert");

	}

	@Override
	public void update(String whereClause, String[] whereArgs) {
		// TODO Auto-generated method stub
		
		new DBTask().execute("update");
	}

	@Override
	public void delete(String where, String[] args) {
		// TODO Auto-generated method stub
	
		new DBTask().execute("delete");

	}

	@Override
	public void select(String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
	
		new DBTask().execute("select");	

	}

	private class DBTask extends AsyncTask<String, Integer, String> {

	

		@Override
		protected String doInBackground(String... operation) {
			// TODO Auto-generated method stub
			if (operation[0].equalsIgnoreCase("insert")) {
				Log.e("DBTask inset", "doInBackground");
				onCreate();
				SQLiteDatabase database = dbHelper.getWritableDatabase();
			
				database.close();

			} else if (operation[0].equalsIgnoreCase("update")) {

				onCreate();
				Log.e("DBTask update", "doInBackground");
				SQLiteDatabase database = dbHelper.getWritableDatabase();
			
				database.close();

			} else if (operation[0].equalsIgnoreCase("delete")) {
				onCreate();
				Log.e("DBTask delete", "doInBackground");
				SQLiteDatabase dataBase = dbHelper.getWritableDatabase();
			
				dataBase.close();

			} 
			else if  (operation[0].equalsIgnoreCase("select"))
			{
			
				onCreate();
				Log.e("DBTask select", "doInBackground");
				SQLiteDatabase database = dbHelper.getReadableDatabase();
				
				
				

			
			}
			

			else {
				Log.e("Invalid db task", "invalid dsfsdfasdas");
			}

			return null;
		}

	}

}
