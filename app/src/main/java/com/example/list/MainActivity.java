package com.example.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //List 모양 or Grid 모양(table 처럼) 만들어주는 애 : layoutManager -> 세로 방향
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 2); // column 2개 격자로!

        recyclerView.setLayoutManager(layoutManager); //recyclerView가 보여주는 모양을 이렇게 설정하겠다

        adapter = new PersonAdapter();

        adapter.addItem(new Person("용미경", "010-2222-3333"));
        adapter.addItem(new Person("김닌자", "010-4444-7777"));
        adapter.addItem(new Person("일남할배", "010-6666-1111"));

        recyclerView.setAdapter(adapter);

        //adapter에 추가한 것을 써주기
        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);
                showToast("아이템 선택됨 : " + item.getName());
            }
        });

    }

    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}