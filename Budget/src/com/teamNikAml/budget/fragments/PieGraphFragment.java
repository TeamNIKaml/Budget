package com.teamNikAml.budget.fragments;

import com.teamNikAml.budget.activity.R;
import com.teamNikAml.budget.graph.PieGraph;
import com.teamNikAml.budget.graph.PieGraph.OnSliceClickedListener;
import com.teamNikAml.budget.graph.PieSlice;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class PieGraphFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View pieView = inflater.inflate(R.layout.pie_frag, container, false);
		init(pieView);

		return pieView;
	}

	private void init(View v) {
		// TODO Auto final Resources resources = getResources();
		final Resources resources = getResources();
		final PieGraph pg = (PieGraph) v.findViewById(R.id.piegraph);
		PieSlice slice ;
		
		
		
		
		int[] color ={R.color.green_light,
				R.color.orange,
				R.color.green,
				R.color.blue,
				R.color.transparent_blue,
				R.color.purple} ;
		
		final String[] month,value;
		//SalesDataSource salesDataSource = SalesDataSource.getSalesDataSource();
	//	month = salesDataSource.getMonth();
	//	value = salesDataSource.getValues();
		
		
		for(int i=0;i<6;i++)
		{
			slice = new PieSlice();
			
			slice.setColor(resources.getColor(color[i]));
			slice.setSelectedColor(resources.getColor(R.color.red));
		//	slice.setValue(Integer.parseInt(value[i]));
			//slice.setTitle(month[i]);
			pg.addSlice(slice);
			
		}
		
	
		
		pg.setOnSliceClickedListener(new OnSliceClickedListener() {

			@Override
			public void onClick(int index) {
				
			}
		});

		Bitmap b = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		pg.setBackgroundBitmap(b);

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
					Log.d("piefrag", "anim end");
				}

				@Override
				public void onAnimationCancel(Animator animation) {// you might
																	// want to
																	// call
																	// slice.setvalue(slice.getGoalValue)
					Log.d("piefrag", "anim cancel");
				}

				@Override
				public void onAnimationRepeat(Animator animation) {

				}
			};
		else
			return null;

	}

}
