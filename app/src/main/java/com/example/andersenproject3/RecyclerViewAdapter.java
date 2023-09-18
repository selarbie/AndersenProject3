package com.example.andersenproject3;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Contact> mList;
    Dialog mDialog;

    public RecyclerViewAdapter(Context mContext, List<Contact> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.details_contact);


        vHolder.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText details_name = (EditText) mDialog.findViewById(R.id.details_name);
                EditText details_number = (EditText) mDialog.findViewById(R.id.details_number);
                Button button = mDialog.findViewById(R.id.btnSave);
                //int position = vHolder.getLayoutPosition();
                details_name.setText(mList.get(vHolder.getLayoutPosition()).getName());
                details_number.setText(mList.get(vHolder.getLayoutPosition()).getNumber());
                mDialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String editName = details_name.getText().toString();
                        String editNumber = details_number.getText().toString();
                        mList.set(vHolder.getLayoutPosition(), new Contact(editName, editNumber));
                        notifyItemChanged(vHolder.getLayoutPosition());
                        mDialog.dismiss();

                    }
                });

            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mList.get(position).getName());
        holder.tv_number.setText(mList.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item_contact;
        private TextView tv_name;
        private TextView tv_number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_contact = (LinearLayout) itemView.findViewById(R.id.contact_item_id);
            tv_name = (TextView) itemView.findViewById(R.id.name_contact);
            tv_number = (TextView) itemView.findViewById(R.id.phone_contact);
        }
    }
}
