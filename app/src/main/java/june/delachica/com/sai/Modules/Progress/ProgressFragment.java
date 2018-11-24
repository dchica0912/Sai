package june.delachica.com.sai.Modules.Progress;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//
//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import june.delachica.com.sai.R;

public class ProgressFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

//        BarChart barChart = view.findViewById(R.id.barGraph);
//
//        ArrayList<BarEntry> barEntries = new ArrayList<>();
//        barEntries.add(new BarEntry(44f, 0));
//        barEntries.add(new BarEntry(88f, 1));
//        barEntries.add(new BarEntry(66f, 2));
//        barEntries.add(new BarEntry(12f, 3));
//        barEntries.add(new BarEntry(19f, 4));
//        barEntries.add(new BarEntry(91f, 5));
//        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");
//
//
//        ArrayList<String> theDates = new ArrayList<>();
//        theDates.add("April");
//        theDates.add("May");
//        theDates.add("June");
//        theDates.add("July");
//        theDates.add("August");
//        theDates.add("September");
//
//        BarData theData = new BarData((IBarDataSet) theDates,barDataSet);
//        barChart.setData(theData);
//
//        barChart.setTouchEnabled(true);


        return view;
    }


}
