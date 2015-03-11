package com.teamNikAml.budget.fragments;





import com.teamNikAml.budget.activity.R;
import com.teamNikAml.budget.graph.Line;
import com.teamNikAml.budget.graph.LineGraph;
import com.teamNikAml.budget.graph.LineGraph.OnPointClickedListener;
import com.teamNikAml.budget.graph.LinePoint;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class LineGraphFragment extends Fragment {
    
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {

	View lineView = inflater.inflate(R.layout.line_frag, container, false);
	init(lineView);

	return lineView;
    }
    
    
    private void init(View v)
    {
	 final Resources resources = getResources();
	 
	final  String[] month,value;
		
	//	SalesDataSource salesDataSource = SalesDataSource.getSalesDataSource();
		//month = salesDataSource.getMonth();
	//	value = salesDataSource.getValues();
	 
	 
	        Line l = new Line();
	        
	        LinePoint p ;
	        
	        int[] color ={R.color.green_light,
					R.color.orange,
					R.color.green,
					R.color.blue,
					R.color.transparent_blue,
					R.color.purple} ;
	        int min,max;
	        
	    //    min = max = Integer.parseInt(value[0]);
	        
	        for(int i=0;i<6;i++)
	        {
	        	 p = new LinePoint();
	 	        p.setX(i*2);
	 	     //   p.setY((Float.parseFloat(value[i])));
	 	        p.setColor(resources.getColor(color[i]));
	 	        l.addPoint(p);
	 	        
	 	      /*  if(min > Integer.parseInt(value[i] ))
	 	        		min = Integer.parseInt(value[i]);
	 	        
	 	       if(max < Integer.parseInt(value[i] ))
	        		max = Integer.parseInt(value[i]);*/
	 	        
	 	        
	        }
	        
	        l.setColor(resources.getColor(color[3]));
	        
	        
	        
	      

		LineGraph li = (LineGraph) v.findViewById(R.id.linegraph);
	        li.setUsingDips(false);
	        li.addLine(l);
	   //     li.setRangeY(min,max);
	        li.setLineToFill(0);

	        li.setOnPointClickedListener(new OnPointClickedListener() {

	            @Override
	            public void onClick(int lineIndex, int pointIndex) {
	             
	            }
	        });
    }

}
