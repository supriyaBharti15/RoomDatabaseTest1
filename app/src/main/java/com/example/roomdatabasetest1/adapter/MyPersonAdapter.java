package com.example.roomdatabasetest1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabasetest1.R;
import com.example.roomdatabasetest1.activity.EditActivity;
import com.example.roomdatabasetest1.listener.DeleteClickListeners;
import com.example.roomdatabasetest1.model.Person;

import java.util.List;

public class MyPersonAdapter extends RecyclerView.Adapter<MyPersonAdapter.MyViewHolderClass> {
    private Context context;
    private List<Person> personList;
    private static DeleteClickListeners listeners;


    public MyPersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public MyViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, parent, false);
        return new MyViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderClass holder, int position) {
        holder.name.setText(personList.get(position).getName());
        holder.email.setText(personList.get(position).getEmail());
        holder.mobileNo.setText(personList.get(position).getPhoneNumber());
        holder.pincode.setText(personList.get(position).getPincode());
        holder.city.setText(personList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    //view holder class
    public class MyViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, email, city, pincode, mobileNo;
        ImageView editBtn, deleteBtn;

        public MyViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.person_name);
            email = itemView.findViewById(R.id.person_email);
            city = itemView.findViewById(R.id.person_city);
            pincode = itemView.findViewById(R.id.person_pincode);
            mobileNo = itemView.findViewById(R.id.person_number);
            editBtn = itemView.findViewById(R.id.edit_Image);
            deleteBtn = itemView.findViewById(R.id.delete_Image);

            //set click Listener
            editBtn.setOnClickListener(this);
            deleteBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.delete_Image:
                    if (listeners != null) {
                        listeners.onDeleteButtonClick(personList.get(getAdapterPosition()).getPrimaryKey());
                    }
                    break;
                case R.id.edit_Image:
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("update", personList.get(getAdapterPosition()).getPrimaryKey());
                    context.startActivity(intent);
                    break;
            }
        }
    }

    public static void onDeleteClickListener(DeleteClickListeners deleteClickListeners) {
        listeners = deleteClickListeners;
    }
}
