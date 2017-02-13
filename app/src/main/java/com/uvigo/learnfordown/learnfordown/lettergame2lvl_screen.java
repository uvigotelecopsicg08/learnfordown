package com.uvigo.learnfordown.learnfordown;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class lettergame2lvl_screen extends AppCompatActivity {
    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> horizontalList;
    private HorizontalAdapter horizontalAdapter;
    private RecyclerView horizontal_recycler_view2;
    private ArrayList<String> horizontalList2;
    private HorizontalAdapter horizontalAdapter2;
    TextView titulo;
    ImageButton BackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettergame2lvl_screen);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Berlin Sans FB Demi Bold.ttf");
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontal_recycler_view2= (RecyclerView) findViewById(R.id.horizontal_recycler_view2);
        titulo = (TextView) findViewById(R.id.textView2);
        titulo.setTypeface(face);

        horizontalList=new ArrayList<String>();
        horizontalList.add("B");
        horizontalList.add("U");
        horizontalList.add("E");
        horizontalList.add("N");
        horizontalList.add("O");

        horizontalAdapter=new HorizontalAdapter(horizontalList);

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontalList2=new ArrayList<String>();
        horizontalList2.add("S");
        horizontalList2.add("D");
        horizontalList2.add("I");
        horizontalList2.add("A");
        horizontalList2.add("S");

        horizontalAdapter2=new HorizontalAdapter(horizontalList2);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(lettergame2lvl_screen.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view2.setLayoutManager(horizontalLayoutManagaer2);





        horizontal_recycler_view.setAdapter(horizontalAdapter);


        horizontal_recycler_view2.setAdapter(horizontalAdapter2);
    }
    public void BackArrow (View v){
        Intent intent1 = new Intent(lettergame2lvl_screen.this, menu_screen.class);
        startActivity(intent1);
    }
}
