package com.example.capstone;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import java.util.ArrayList;

public class FirstFragment extends Fragment {
    public static final float MAX = 15, MIN =0.1f;
    private RadarChart radarChart;
    private int page;
    private String name;
    private float v1,v2,v3,v4,v5;
    public static FirstFragment newInstance(String name,float v1,float v2,float v3,float v4,float v5){
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("name",name);
        args.putFloat("v1",v1);
        args.putFloat("v2",v2);
        args.putFloat("v3",v3);
        args.putFloat("v4",v4);
        args.putFloat("v5",v5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt("someInt",0);
        name = getArguments().getString("name");
        v1 = getArguments().getFloat("v1",0);
        v2 = getArguments().getFloat("v2",0);
        v3 = getArguments().getFloat("v3",0);
        v4 = getArguments().getFloat("v4",0);
        v5 = getArguments().getFloat("v5",0);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        radarChart = view.findViewById(R.id.radarChart);
        radarChart.getDescription().setEnabled(false);
        radarChart.setWebLineWidth(1.5f);
        radarChart.setWebColor(Color.DKGRAY);
        radarChart.setWebLineWidth(1.5f);
        radarChart.setWebColorInner(Color.DKGRAY);
        radarChart.setWebAlpha(200);
        radarChart.setTouchEnabled(false);
        String[] label = new String[] {"생활·편의·교통","교육","복지 문화","자연","안전"};
        setData();

        radarChart.animateXY(1000,1000, Easing.EaseInOutQuad,Easing.EaseInOutQuad);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0);
        xAxis.setXOffset(0);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = radarChart.getYAxis();
        yAxis.setLabelCount(5,true);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(MIN);
        yAxis.setAxisMaximum(MAX);
        yAxis.setDrawLabels(true);

        Legend l = radarChart.getLegend();
        l.setTextSize(15f);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);
        return view;
    }
    private void setData(){
        ArrayList<RadarEntry> main = new ArrayList<>();
        ArrayList<RadarEntry> val = new ArrayList<>();
        for(int i=0; i<5; i++){
            main.add(new RadarEntry(5));
        }
        val.add(new RadarEntry((v1)));
        val.add(new RadarEntry((v2)));
        val.add(new RadarEntry((v3)));
        val.add(new RadarEntry((v4)));
        val.add(new RadarEntry((v5)));

        RadarDataSet set1 =new RadarDataSet(main, "평균");
        set1.setValueTextSize(2f);
        set1.setValueTextColor(Color.RED);
        set1.setColor(0xAAc17774);
        set1.setFillColor(0xAAc17774);
        set1.setDrawFilled(true);
        set1.setFillAlpha(100);
        set1.setLineWidth(2f);
        set1.setDrawHighlightIndicators(false);
        set1.setDrawHighlightCircleEnabled(true);


        RadarDataSet set2 =new RadarDataSet(val, name);
        set2.setColor(0xAA7c99c7);
        set2.setFillColor(0xAA7c99c7);
        set2.setDrawFilled(true);
        set2.setFillAlpha(100);
        set2.setLineWidth(2f);
        set2.setDrawHighlightIndicators(false);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setValueTextSize(2f);
        set2.setValueTextColor(Color.BLACK);


        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);

        radarChart.setData(data);
        radarChart.invalidate();

    }
}