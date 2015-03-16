package com.teamNikAml.budget.graph.fragment;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class PieGraphFragment  extends Fragment {

	
	private int[] pieChartValues;

	private String[] pieFragmenttName;

	public static final String TYPE = "type";
	private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE,
			Color.MAGENTA, Color.CYAN };
	private CategorySeries mSeries = new CategorySeries("");
	private DefaultRenderer mRenderer = new DefaultRenderer();
	private GraphicalView mChartView;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		init();
		

		return drawLingGraph();
	}

	public void init() {
		// TODO Auto-generated method stub
		
		
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
		mRenderer.setAxesColor(Color.RED);
		mRenderer.setChartTitle("Sales");
		mRenderer.setChartTitleTextSize(32);
		mRenderer.setLabelsTextSize(33);
		mRenderer.setLegendTextSize(30);
		mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setStartAngle(90);

		if (mChartView == null) {
		
			mChartView = ChartFactory.getPieChartView(getActivity().getApplicationContext(), mSeries, mRenderer);
			mRenderer.setClickEnabled(true);
			mRenderer.setSelectableBuffer(10);

			setListner();
			

		} else {
			mChartView.repaint();
		}
		fillPieChart();
		
	}

	public View drawLingGraph() {

		return mChartView;
	}
	
	
	
	private void setListner() {
		// TODO Auto-generated method stub
		mChartView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SeriesSelection seriesSelection = mChartView
						.getCurrentSeriesAndPoint();
				if (seriesSelection == null) {
					Toast.makeText(getActivity().getApplicationContext(),
							"No chart element selected", Toast.LENGTH_SHORT)
							.show();
				} else {
					for (int i = 0; i < mSeries.getItemCount(); i++) {
						mRenderer.getSeriesRendererAt(i).setHighlighted(
								i == seriesSelection.getPointIndex());
					}
					mChartView.repaint();
					Toast.makeText(
							getActivity().getApplicationContext(),
							pieFragmenttName[seriesSelection.getPointIndex()] + " : "
									+ seriesSelection.getValue(),
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	public void fillPieChart() {
		for (int i = 0; i < pieChartValues.length; i++) {
			mSeries.add(pieFragmenttName[i], pieChartValues[i]);
			SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
			renderer.setColor(COLORS[(mSeries.getItemCount() - 1)
					% COLORS.length]);
			mRenderer.addSeriesRenderer(renderer);
			if (mChartView != null)
				mChartView.repaint();
		}
	}

	public int[] getPieChartValues() {
		return pieChartValues;
	}

	public void setPieChartValues(int[] pieChartValues) {
		this.pieChartValues = pieChartValues;
	}

	public String[] getPieFragmenttName() {
		return pieFragmenttName;
	}

	public void setPieFragmenttName(String[] pieFragmenttName) {
		this.pieFragmenttName = pieFragmenttName;
	}

	

}
