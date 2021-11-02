package com.example.list;

import static android.util.Log.*;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//RecyclerView가 갖고있는 여러개의 아이템을 관리하는 것은 Adapter로 사용! Data도 물론 여기서
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private static final String TAG = "PersonAdapter"; // log용 태그 생성

    //Data
    ArrayList<Person> items = new ArrayList<Person>();

    //listener 변수 선언
    OnPersonItemClickListener listener;

    //Adapter에 Person 객체 추가 메서드
    public void addItem(Person item){
        items.add(item);

        d(TAG, " addItem 실행 ----------------------");
    }

    public void setItems(ArrayList<Person> items){
        this.items = items;

        d(TAG, " setItems 실행 ----------------------");
    }

    public Person getItem(int position) {

        d(TAG, " getItem 실행 ----------------------");

        return items.get(position);

    }

    public void setItem(int position, Person person){
        items.set(position, person);

        d(TAG, " setItem 실행 ----------------------");
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override //각각의 item을 담을 viewHolder에 넣어서 return 해줘라
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        d(TAG, " onCreateViewHolder 실행 ----------------------");

        return new ViewHolder(itemView, listener); // 생성자 생성
    }

    @Override //스크롤 내릴떄마다 viewHolder를 계속 만들지 않고 (메모리 늘어나지 않도록) 재사용하기 위한 메서드
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);

        d(TAG, " onBindViewHolder 실행 ----------------------");
    }

    @Override //객체의 갯수, arrayList니까 size
    public int getItemCount() {
        d(TAG, " getItemCount 실행 ----------------------");

        return items.size();
    }


    //하나의 item의 view를 조작하는 기능을 하는 것 = ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{

        //private static final String TAG2 = "Adapter inner Class - ViewHolder"; // log용 태그 생성

        TextView textView;
        TextView textView2;

        //생성자 + 이벤트처리를 해줄 listener (추가 parameter) 까지 받는 생성자 추가
        public ViewHolder(@NonNull View itemView, final OnPersonItemClickListener listener) {

            super(itemView);

            Log.d(TAG, "ViewHolder 실행~~~~");

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            //아이템 뷰가 각각 눌렸을때 처리하도록 리스너에게 넘김
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d(TAG, "setOnClickListener 실행~~~~");

                    int position = getAdapterPosition(); //몇번째 view인지 index값이 넘어옴

                    if (listener != null){ //만들어준 인터페이스 함수
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item){

            Log.d(TAG, "setItem 실행~~~~");

            textView.setText(item.getName());
            textView2.setText(item.getPhone());
        }
    }

}
