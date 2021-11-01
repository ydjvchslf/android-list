package com.example.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//RecyclerView가 갖고있는 여러개의 아이템을 관리하는 것은 Adapter로 사용! Data도 물론 여기서
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    //Data
    ArrayList<Person> items = new ArrayList<Person>();

    //listener 변수 선언
    OnPersonItemClickListener listener;

    //Adapter에 Person 객체 추가 메서드
    public void addItem(Person item){
        items.add(item);
    }

    public void setItems(ArrayList<Person> items){
        this.items = items;
    }

    public Person getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Person person){
        items.set(position, person);
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override //각각의 item을 담을 viewHolder에 넣어서 return 해줘라
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        return new ViewHolder(itemView, listener); // 생성자 parameter 2개 받는걸로 수정되었음
    }

    @Override //스크롤 내릴떄마다 viewHolder를 계속 만들지 않고 (메모리 늘어나지 않도록) 재사용하기 위한 메서드
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override //객체의 갯수, arrayList니까 size
    public int getItemCount() {
        return items.size();
    }

    //하나의 item의 view를 조작하는 기능을 하는 것 = ViewHolder
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        //생성자 + 이벤트처리를 해줄 listener (추가 parameter) 까지 받는 생성자 추가
        public ViewHolder(@NonNull View itemView, final OnPersonItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            //아이템 (view)가 클릭되는 시점 -> listner쪽으로 정보를 전달할 수 있다
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item){
            textView.setText(item.getName());
            textView2.setText(item.getPhone());
        }
    }

}
