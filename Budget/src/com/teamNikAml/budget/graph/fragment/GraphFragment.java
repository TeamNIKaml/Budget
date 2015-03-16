package com.teamNikAml.budget.graph.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.teamNikAml.budget.activity.R;

public class GraphFragment extends Fragment {

	private ArrayAdapter<String> graphAdaptor,valueAdaptor;
	private List<String> graphList = new ArrayList<String>();

	private List<String> valueList = new ArrayList<String>();

	private LineGraphFragment lineGraphFragment;

	private BarGraphFragment barGraphFragment;

	private PieGraphFragment pieGraphFragment;

	private Spinner graphSpinner,valueSpinner;

	private String[] heading = { "Jan", "Feb", "Mar", "Apr", "May", "Jun" };

	private double[] x = { 1, 2, 3, 4, 5, 6 };
	private double[] income = { 2000, 2500, 2700, 3000, 2800, 3500 };

	private double[] expense = { 1000, 2500, 3700, 2000, 2300, 2500 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_graph_container,
				container, false);

		init(rootView);

		return rootView;
	}

	private void init(View v) {
		// TODO Auto-generated method stub
		graphSpinner = (Spinner) v.findViewById(R.id.graphSpinner);
		
		valueSpinner = (Spinner) v.findViewById(R.id.valueSpinner);

		graphList.add("Line Graph");
		graphList.add("Bar Graph");
		graphList.add("Pie Chart");
		
		
		valueList.add("Income");
		valueList.add("Expense");
		valueList.add("Income vs Expense");

		graphAdaptor = new ArrayAdapter<String>(getActivity()
				.getApplicationContext(), R.layout.spinnertext, graphList);

		graphSpinner.setAdapter(graphAdaptor);
		
		
		valueAdaptor = new ArrayAdapter<String>(getActivity().getApplicationContext(),
				R.layout.spinnertext, valueList);

		valueSpinner.setAdapter(valueAdaptor);

		setLineGraphData("income");

		getFragmentManager().beginTransaction()
				.add(R.id.graph_container, lineGraphFragment).commit();

		checkFragment();

		graphSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub

				checkFragment();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		valueSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				// TODO Auto-generated method stub

				checkFragment();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		

	}

	private void setPieGraphData(String name) {
		
		pieGraphFragment = new PieGraphFragment();

		if (name.equalsIgnoreCase("income")) {
			int[] income = { 2000, 2500, 2700, 3000, 2800, 3500 };

			String[] pieName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun" };

			pieGraphFragment.setPieChartValues(income);
			pieGraphFragment.setPieFragmenttName(pieName);
		}

		else if (name.equalsIgnoreCase("expense")) {
			int[] expense = { 1000, 3500, 1700, 2000, 3800, 4500 };
			String[] pieName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun" };

			pieGraphFragment.setPieChartValues(expense);
			pieGraphFragment.setPieFragmenttName(pieName);
		}

		else {
			int[] value = { 16528, 13234 };
			String[] pieName = { "Income", "Expense" };

			pieGraphFragment.setPieChartValues(value);
			pieGraphFragment.setPieFragmenttName(pieName);

		}

		

	}

	private void setBarGraphData(String name) {
		// TODO Auto-generated method stub

		barGraphFragment = new BarGraphFragment();

		double[] income = { 2000, 2500, 2700, 3000, 2800, 3500 };
		double[] expense = { 1000, 3500, 1700, 2000, 3800, 4500 };

		String[] heading = { "Jan", "Feb", "Mar", "Apr", "May", "Jun" };

		if (name.equalsIgnoreCase("income")) {

			barGraphFragment.setxHeading(heading);
			barGraphFragment.setyValue(income);

			barGraphFragment.SeriesAdd(name);
			barGraphFragment.SeriesRendererAdd(Color.BLUE, true);
			barGraphFragment.MultipleSeriesRendererInit(
					Color.argb(100, 50, 50, 50), Color.BLUE);
			barGraphFragment.MultipleSeriesRendererAdd();

		}

		else if (name.equalsIgnoreCase("expense")) {

			barGraphFragment.setxHeading(heading);
			barGraphFragment.setyValue(expense);

			barGraphFragment.SeriesAdd(name);
			barGraphFragment.SeriesRendererAdd(Color.GREEN, true);
			barGraphFragment.MultipleSeriesRendererInit(
					Color.argb(100, 50, 50, 50), Color.BLUE);
			barGraphFragment.MultipleSeriesRendererAdd();

		}

		else {

			barGraphFragment.setxHeading(heading);
			barGraphFragment.setyValue(income);

			barGraphFragment.SeriesAdd(name);
			barGraphFragment.SeriesRendererAdd(Color.BLUE, true);
			barGraphFragment.MultipleSeriesRendererInit(
					Color.argb(100, 50, 50, 50), Color.BLUE);
			barGraphFragment.MultipleSeriesRendererAdd();

			barGraphFragment.setxHeading(heading);
			barGraphFragment.setyValue(expense);

			barGraphFragment.SeriesAdd(name);
			barGraphFragment.SeriesRendererAdd(Color.GREEN, true);
			barGraphFragment.MultipleSeriesRendererAdd();

		}

	}

	private void setLineGraphData(String name) {
		// TODO Auto-generated method stub

		lineGraphFragment = new LineGraphFragment();

		if (name.equalsIgnoreCase("income")) {
			lineGraphFragment.setxValue(x);
			lineGraphFragment.setyValue(income);
			lineGraphFragment.setxHeading(heading);

			lineGraphFragment.SeriesAdd(name);
			lineGraphFragment.SeriesRendererAdd(Color.WHITE, true);
			lineGraphFragment.MultipleSeriesRendererInit(
					Color.argb(100, 50, 50, 50), Color.BLUE);
			lineGraphFragment.MultipleSeriesRendererAdd();
		}

		else if (name.equalsIgnoreCase("expense")) {

			lineGraphFragment.setxValue(x);
			lineGraphFragment.setyValue(expense);
			lineGraphFragment.setxHeading(heading);

			lineGraphFragment.SeriesAdd(name);
			lineGraphFragment.SeriesRendererAdd(Color.GREEN, true);
			lineGraphFragment.MultipleSeriesRendererInit(
					Color.argb(100, 50, 50, 50), Color.BLUE);
			lineGraphFragment.MultipleSeriesRendererAdd();

		}

		else {

			lineGraphFragment.setxValue(x);
			lineGraphFragment.setyValue(income);
			lineGraphFragment.setxHeading(heading);

			lineGraphFragment.SeriesAdd(name);
			lineGraphFragment.SeriesRendererAdd(Color.WHITE, true);
			lineGraphFragment.MultipleSeriesRendererInit(
					Color.argb(100, 50, 50, 50), Color.BLUE);
			lineGraphFragment.MultipleSeriesRendererAdd();

			lineGraphFragment.setyValue(expense);

			lineGraphFragment.SeriesAdd("expense");
			lineGraphFragment.SeriesRendererAdd(Color.GREEN, true);
			lineGraphFragment.MultipleSeriesRendererAdd();

		}

	}

	private void checkFragment() {
		// TODO Auto-generated method stub

		if (graphSpinner.getSelectedItem().toString() == "Bar Graph") {

			setBarGraphData(valueSpinner.getSelectedItem().toString());
			getFragmentManager().beginTransaction()
					.replace(R.id.graph_container, barGraphFragment).commit();

		} else if (graphSpinner.getSelectedItem().toString() == "Line Graph") {

			setLineGraphData(valueSpinner.getSelectedItem().toString());

			getFragmentManager().beginTransaction()
					.replace(R.id.graph_container, lineGraphFragment).commit();

		} else {
			setPieGraphData(valueSpinner.getSelectedItem().toString());
			getFragmentManager().beginTransaction()
					.replace(R.id.graph_container, pieGraphFragment).commit();

		}

	}

}
