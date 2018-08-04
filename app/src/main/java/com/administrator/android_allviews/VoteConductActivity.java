package com.administrator.android_allviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.administrator.widget.VoteConductView;

import java.util.ArrayList;

public class VoteConductActivity extends AppCompatActivity {
    ArrayList<Integer> iList = new ArrayList<>();//每个排行柱的详情票数
    ArrayList<String> sList = new ArrayList<>();//每个排行柱的投票名称
    VoteConductView conductView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_conduct);
        conductView = findViewById(R.id.vcv_view);

        iList.add(150);
        iList.add(120);
        iList.add(140);
        iList.add(80);
        iList.add(44);

        sList.add("DNF");
        sList.add("CF");
        sList.add("LOL");
        sList.add("QQ");
        sList.add("TX");

        conductView.setiList(iList,sList);


    }
}
