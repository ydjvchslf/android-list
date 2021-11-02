package com.example.list;

import android.view.View;

// list를 눌렀을때 이벤트 처리를 하기 위한 기능을 가진 인터페이스 생성
public interface OnPersonItemClickListener {

    //item이 하나 클릭되면 이 함수가 호출되도록 할것이야
    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position);

}
