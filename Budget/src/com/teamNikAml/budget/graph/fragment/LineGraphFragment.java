package com.teamNikAml.budget.graph.fragment;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Fragment;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LineGraphFragment extends Fragment {

	private String[] xHeading;
	private double[] xValue, yValue;

	private XYSeries xyseries;

	private XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

	private XYSeriesRenderer xySeriesRenderer;

	private XYMultipleSeriesRenderer multiRenderer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return drawLingGraph();
	}

	public View drawLingGraph() {

		return ChartFactory.getLineChartView(getActivity()
				.getApplicationContext(), dataset, multiRenderer);
	}

	public void SeriesAdd(String name) {

		xyseries = new XYSeries(name);

		for (int i = 0; i < xValue.length; i++)
			xyseries.add(xValue[i], yValue[i]);

		dataset.addSeries(xyseries);

	}

	public void SeriesRendererAdd(int color, Boolean displayChartValue) {
		xySeriesRenderer = new XYSeriesRenderer();

		xySeriesRenderer.setColor(color);
		xySeriesRenderer.setChartValuesTextSize(22);
		xySeriesRenderer.setChartValuesSpacing(22);
		xySeriesRenderer.setChartValuesTextAlign(Align.CENTER);
		xySeriesRenderer.setPointStyle(PointStyle.SQUARE);
		xySeriesRenderer.setFillPoints(true);
		xySeriesRenderer.setLineWidth(2);
		xySeriesRenderer.setDisplayChartValues(displayChartValue);
	}

	public void MultipleSeriesRendererInit(int backgroundColor, int labelColor) {

		multiRenderer = new XYMultipleSeriesRenderer();

		multiRenderer.setApplyBackgroundColor(true);
		multiRenderer.setBackgroundColor(backgroundColor);
		multiRenderer.setXLabelsColor(labelColor);
		multiRenderer.setLabelsColor(labelColor);
		multiRenderer.setXLabelsPadding(22);

		multiRenderer.setXLabels(0);
		multiRenderer.setChartTitleTextSize(22);
		multiRenderer.setLabelsTextSize(22);
		multiRenderer.setLegendTextSize(30);
		multiRenderer.setAxisTitleTextSize(20);
		multiRenderer.setZoomButtonsVisible(true);

		for (int i = 0; i < xValue.length; i++)
			multiRenderer.addXTextLabel(i + 1, xHeading[i]);

	}

	public void MultipleSeriesRendererAdd() {

		multiRenderer.addSeriesRenderer(xySeriesRenderer);
	}

	public String[] getxHeading() {
		return xHeading;
	}

	public void setxHeading(String[] xHeading) {
		this.xHeading = xHeading;
	}

	public double[] getxValue() {
		return xValue;
	}

	public void setxValue(double[] xValue) {
		this.xValue = xValue;
	}

	public double[] getyValue() {
		return yValue;
	}

	public void setyValue(double[] yValue) {
		this.yValue = yValue;
	}

}
