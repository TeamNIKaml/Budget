package com.teamNikAml.budget.balanaceSheet.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamNikAml.budget.activity.R;

public class BalanceSheetFragment extends Fragment {

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_balance_sheet, container,
				false);

		return rootView;
	}
}
