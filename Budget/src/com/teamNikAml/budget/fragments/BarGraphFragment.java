package com.teamNikAml.budget.fragments;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.teamNikAml.budget.activity.R;
import com.teamNikAml.budget.graph.Bar;
import com.teamNikAml.budget.graph.BarGraph;
import com.teamNikAml.budget.graph.BarGraph.OnBarClickedListener;
import com.teamNikAml.budget.graph.HoloGraphAnimate;

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
		Bar bar = new Bar();
		bar.setColor(getResources().getColor(R.color.green_light));
		bar.setSelectedColor(getResources()
				.getColor(R.color.transparent_orange));
		bar.setName("Test1");
		bar.setValue(1000);
		bar.setValueString("$1,000");
		aBars.add(bar);
		bar = new Bar();
		bar.setColor(getResources().getColor(R.color.orange));
		bar.setName("Test2");
		bar.setValue(2000);
		bar.setValueString("$2,000");
		aBars.add(bar);
		bar = new Bar();
		bar.setColor(getResources().getColor(R.color.purple));
		bar.setName("Test3");
		bar.setValue(1500);
		bar.setValueString("$1,500");
		aBars.add(bar);

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
		Button animateBarButton = (Button) v
				.findViewById(R.id.animateBarButton);
		Button animateInsertBarButton = (Button) v
				.findViewById(R.id.animateInsertBarButton);
		Button animateDelteBarButton = (Button) v
				.findViewById(R.id.animateDeleteBarButton);

		// animate to random values
		animateBarButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * for (Bar b : barGraph.getBars()) { b.setGoalValue((float)
				 * Math.random() * 1000); b.setValuePrefix("$");// display the
				 * prefix throughout the // animation Log.d("goal val",
				 * String.valueOf(b.getGoalValue())); }
				 * barGraph.setDuration(1200);// default if unspecified is 300
				 * ms barGraph.setInterpolator(new
				 * AccelerateDecelerateInterpolator());// Only // use //
				 * over/undershoot // when // not // inserting/deleting
				 * barGraph.setAnimationListener(getAnimationListener());
				 * barGraph.animateToGoalValues();
				 */

				Bitmap bitmap = barGraph.getBitmap();
				int BUFFER_SIZE = 1024 * 8;
				File file = new File(getActivity().getApplicationContext()
						.getFilesDir() + "/" + "myBarGraph.jpg");
				try {
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					final BufferedOutputStream bos = new BufferedOutputStream(
							fos, BUFFER_SIZE);
					bitmap.compress(CompressFormat.JPEG, 100, bos);
					bos.flush();
					bos.close();
					fos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {

				}

			}
		});

		// insert a bar
		animateInsertBarButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				barGraph.cancelAnimating(); // must clear existing to call
				// onAnimationEndListener cleanup
				// BEFORE adding new bars
				int newPosition = barGraph.getBars().size() == 0 ? 0
						: new Random().nextInt(barGraph.getBars().size());
				Bar bar = new Bar();
				bar.setColor(Color.parseColor("#AA0000FF"));
				bar.setName("Insert bar "
						+ String.valueOf(barGraph.getBars().size()));
				bar.setValue(0);
				bar.mAnimateSpecial = HoloGraphAnimate.ANIMATE_INSERT;
				barGraph.getBars().add(newPosition, bar);
				for (Bar b : barGraph.getBars()) {
					b.setGoalValue((float) Math.random() * 1000);
					b.setValuePrefix("$");// display the prefix throughout the
					// animation
					Log.d("goal val", String.valueOf(b.getGoalValue()));
				}
				barGraph.setDuration(1200);// default if unspecified is 300 ms
				barGraph.setInterpolator(new AccelerateDecelerateInterpolator());// Don't
				// use
				// over/undershoot
				// interpolator
				// for
				// insert/delete
				barGraph.setAnimationListener(getAnimationListener());
				barGraph.animateToGoalValues();

			}
		});

		// delete a bar
		animateDelteBarButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				barGraph.cancelAnimating(); // must clear existing to call
				// onAnimationEndListener cleanup
				// BEFORE adding new bars
				if (barGraph.getBars().size() == 0)
					return;

				for (Bar b : barGraph.getBars()) {
					b.setGoalValue((float) Math.random() * 1000);
					b.setValuePrefix("$");// display the prefix throughout the
					// animation
					Log.d("goal val", String.valueOf(b.getGoalValue()));
				}
				int newPosition = new Random().nextInt(barGraph.getBars()
						.size());
				Bar bar = barGraph.getBars().get(newPosition);
				bar.mAnimateSpecial = HoloGraphAnimate.ANIMATE_DELETE;
				bar.setGoalValue(0);// animate to 0 then delete
				barGraph.setDuration(1200);// default if unspecified is 300 ms
				barGraph.setInterpolator(new AccelerateInterpolator());// Don't
				// use
				// over/undershoot
				// interpolator
				// for
				// insert/delete
				barGraph.setAnimationListener(getAnimationListener());
				barGraph.animateToGoalValues();

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
