package com.example.list;

import android.view.View;

// list를 눌렀을때 이벤트 처리를 하기 위한 기능을 가진 인터페이스 생성
public interface OnPersonItemClickListener {

    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position);

}
