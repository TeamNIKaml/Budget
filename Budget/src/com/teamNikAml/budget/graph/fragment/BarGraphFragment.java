package com.teamNikAml.budget.graph.fragment;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BarGraphFragment extends Fragment {

	private String[] xHeading;
	private double[] yValue;

	private CategorySeries series;

	private XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

	private XYSeriesRenderer renderer;

	private XYMultipleSeriesRenderer mRenderer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return drawLingGraph();
	}

	public View drawLingGraph() {
		
		
		if(dataset == null || mRenderer == null )
		{
			System.out.println("Dataset or render null");
			return new View(getActivity().getApplicationContext());
		}

		return ChartFactory.getBarChartView(getActivity()
				.getApplicationContext(), dataset, mRenderer, Type.DEFAULT);
	}

	public void SeriesAdd(String name) {

		series = new CategorySeries(name);
		for (int i = 0; i < yValue.length; i++) {
			  series.add("Bar"+(i+1),yValue[i]);
		}

		dataset.addSeries(series.toXYSeries());

	}

	public void SeriesRendererAdd(int color, Boolean displayChartValue) {
		renderer = new XYSeriesRenderer();

		renderer = new XYSeriesRenderer(); // one renderer for one series
		renderer.setColor(color);
		renderer.setDisplayChartValues(displayChartValue);
		renderer.setChartValuesSpacing((float) 5.5d);
		renderer.setLineWidth((float) 10.5d);
		renderer.setChartValuesTextSize(22);
	}

	public void MultipleSeriesRendererInit(int backgroundColor, int labelColor) {

		mRenderer = new XYMultipleSeriesRenderer();

		// collection multiple values for one renderer or series
		

		mRenderer.setChartTitleTextSize(32);
		mRenderer.setLabelsTextSize(33);
		mRenderer.setLegendTextSize(30);

		mRenderer.setZoomButtonsVisible(true);
		// mRenderer.setShowLegend(true);
		// mRenderer.setShowGridX(true);
		// mRenderer.setShowGridY(true);

		// mRenderer.setBarSpacing(.5);
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(backgroundColor);
		// mRenderer.setXAxisMin(0);

		// mRenderer.setXAxisMax(5);
		// mRenderer.setYAxisMax(50);

		mRenderer.setXLabels(0);

		for (int i = 0; i < yValue.length; i++)
			mRenderer.addXTextLabel(i + 1, xHeading[i]);

		mRenderer.setXLabelsColor(labelColor);
		mRenderer.setXLabelsPadding(22);

	}

	public void MultipleSeriesRendererAdd() {

		mRenderer.addSeriesRenderer(renderer);
		
		
	}

	public String[] getxHeading() {
		return xHeading;
	}

	public void setxHeading(String[] xHeading) {
		this.xHeading = xHeading;
	}

	public double[] getyValue() {
		return yValue;
	}

	public void setyValue(double[] yValue) {
		this.yValue = yValue;
	}

}
