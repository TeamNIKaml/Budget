package com.teamNikAml.budget.addCategory.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamNikAml.budget.activity.R;

public class AddCatagoryFragment extends Fragment {

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_add_catagory, container,
				false);

		return rootView;
	}
}
