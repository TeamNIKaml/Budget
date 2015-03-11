package com.teamNikAml.budget.fragments;

import java.util.ArrayList;

import com.teamNikAml.budget.activity.R;
import com.teamNikAml.budget.graph.Bar;
import com.teamNikAml.budget.graph.BarGraph;
import com.teamNikAml.budget.graph.BarGraph.OnBarClickedListener;
import com.teamNikAml.budget.graph.HoloGraphAnimate;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class BarGraphFragment extends Fragment {
    private BarGraph bg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {

	View barView = inflater.inflate(R.layout.bar_frag, container, false);
	init(barView);

	return barView;
    }

    private void init(View v) {
	// TODO Auto-generated method stub

	ArrayList<Bar> aBars = new ArrayList<Bar>();
	Bar bar;
	String[] month,value;
	
	//SalesDataSource salesDataSource = SalesDataSource.getSalesDataSource();
	//month = salesDataSource.getMonth();
	//value = salesDataSource.getValues();
	
	
	for(int i=0;i<6;i++)
	{
		bar = new Bar();
		bar.setColor(getResources().getColor(R.color.blue));
		bar.setSelectedColor(getResources()
			.getColor(R.color.green_light));
	//	bar.setName(month[i]);
	//	bar.setValue(Integer.parseInt(value[i]));
	//	bar.setValueString("€  "+value[i]);
		aBars.add(bar);
	}
	
	
	
	
	
	
	
	

	final BarGraph barGraph = (BarGraph) v.findViewById(R.id.bargraph);
	bg = barGraph;
	barGraph.setBars(aBars);

	barGraph.setOnBarClickedListener(new OnBarClickedListener() {

	    @Override
	    public void onClick(int index) {
		Toast.makeText(
			getActivity(),
			"Bar "
				+ index
				+ " clicked "
				+ String.valueOf(barGraph.getBars().get(index)
					.getValue()), Toast.LENGTH_SHORT)
			.show();
	    }
	});


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public Animator.AnimatorListener getAnimationListener() {
	if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
	    return new Animator.AnimatorListener() {
		@Override
		public void onAnimationStart(Animator animation) {

		}

		@Override
		public void onAnimationEnd(Animator animation) {
		    ArrayList<Bar> newBars = new ArrayList<Bar>();
		    // Keep bars that were not deleted
		    for (Bar b : bg.getBars()) {
			if (b.mAnimateSpecial != HoloGraphAnimate.ANIMATE_DELETE) {
			    b.mAnimateSpecial = HoloGraphAnimate.ANIMATE_NORMAL;
			    newBars.add(b);
			}
		    }
		    bg.setBars(newBars);
		}

		@Override
		public void onAnimationCancel(Animator animation) {
		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	    };
	else
	    return null;

    }

}
