package com.administrator.android_allviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.administrator.widget.HotRecommendViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HotSearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_hot_search);

        HotRecommendViewGroup grvg_test = findViewById(R.id.grvg_test);
        final EditText et_home_search = findViewById(R.id.et_home_search);
        List<String> date = new ArrayList<>();
        date.add("腾讯");
        date.add("马化腾");
        date.add("光电工作室");
        date.add("天美工作室");
        date.add("游戏制作");
        date.add("天美工作室");
        date.add("马化腾");
        date.add("腾讯");
        grvg_test.setData(date);

        grvg_test.setMyClickListener(new HotRecommendViewGroup.MyClickListener() {

            @Override
            public void onClick(String tv) {
                et_home_search.setText(tv);
            }
        });
    }
}
