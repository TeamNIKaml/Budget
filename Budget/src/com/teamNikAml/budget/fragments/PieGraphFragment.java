package com.teamNikAml.budget.fragments;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.teamNikAml.budget.activity.R;
import com.teamNikAml.budget.graph.PieGraph;
import com.teamNikAml.budget.graph.PieGraph.OnSliceClickedListener;
import com.teamNikAml.budget.graph.PieSlice;

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
		final Button animateButton = (Button) v
				.findViewById(R.id.animatePieButton);
		PieSlice slice = new PieSlice();
		slice.setColor(resources.getColor(R.color.green_light));
		slice.setSelectedColor(resources.getColor(R.color.transparent_orange));
		slice.setValue(2);
		slice.setTitle("first");
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(resources.getColor(R.color.orange));
		slice.setValue(3);
		pg.addSlice(slice);
		slice = new PieSlice();
		slice.setColor(resources.getColor(R.color.purple));
		slice.setValue(8);
		pg.addSlice(slice);

		pg.setOnSliceClickedListener(new OnSliceClickedListener() {

			@Override
			public void onClick(int index) {
				Toast.makeText(getActivity(), "Slice " + index + " clicked",
						Toast.LENGTH_SHORT).show();
			}
		});

		Bitmap b = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		pg.setBackgroundBitmap(b);

		SeekBar seekBar = (SeekBar) v.findViewById(R.id.seekBarRatio);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				pg.setInnerCircleRatio(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		seekBar = (SeekBar) v.findViewById(R.id.seekBarPadding);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				pg.setPadding(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
			animateButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					/*
					 * for (PieSlice s : pg.getSlices())
					 * s.setGoalValue((float)Math.random() * 10);
					 * pg.setDuration(1000);//default if unspecified is 300 ms
					 * pg.setInterpolator(new
					 * AccelerateDecelerateInterpolator());//default if
					 * unspecified is linear; constant speed
					 * pg.setAnimationListener(getAnimationListener());
					 * pg.animateToGoalValues();//animation will always
					 * overwrite. Pass true to call the onAnimationCancel
					 * Listener with onAnimationEnd
					 */

					Bitmap bitmap = pg.getBitmap();
					int BUFFER_SIZE = 1024 * 8;
					File file = new File(getActivity().getApplicationContext()
							.getFilesDir() + "/" + "myPieGraph.jpg");
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
