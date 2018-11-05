package com.example.yoshino.recyclerviewretrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoshino.recyclerviewretrofit.model.User;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterHolder> {

    private List<User> mUserList;

    private OnItemClickLister mLister;
    Context mContext;
    public static class UserAdapterHolder extends  RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mNombre;
        public TextView mNick;
        public TextView mDate;

        public UserAdapterHolder(View itemView, final OnItemClickLister lister) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagenPica);
            mNombre = itemView.findViewById(R.id.txtNombre);
            mNick = itemView.findViewById(R.id.txtNick);
            mDate = itemView.findViewById(R.id.txtDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (lister != null){
                        int position = getAdapterPosition();
                       if (position!= RecyclerView.NO_POSITION){
                           lister.onItemClick(position);
                       }
                    }
                }
            });
//            mImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (lister != null){
//                        int position = getAdapterPosition();
//                        if (position!= RecyclerView.NO_POSITION){
//                            lister.onDeleteClick(position);
//                        }
//                    }
//                }
//            });
        }
    }

    public void setOnItemClick(OnItemClickLister lister){
        mLister = lister;

    }

    public UserAdapter (List<User> userList,Context context){
        mUserList = userList;
        mContext = context;
    }


    @NonNull
    @Override
    public UserAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        UserAdapterHolder evk = new UserAdapterHolder(v,mLister);
        return  evk;

    }

    public interface OnItemClickLister {
        void  onItemClick(int position);
//        void  onDeleteClick(int position);
    }




    @Override
    public void onBindViewHolder(@NonNull UserAdapterHolder holder, int position) {
        User userItem = mUserList.get(position);
        holder.mNombre.setText(userItem.getName());
        holder.mNick.setText(userItem.getNick());
        holder.mDate.setText(userItem.getDate());

     String url = "https://www.pasionfutbol.com/__export/1532545089176/sites/pasionlibertadores/img/2018/07/25/mario-ricardo-cristobal-colon-de-paraguay-1_2.jpg_1151390924.jpg";
        Picasso.with(mContext).load(url).into(holder.mImageView);
        Picasso.with(mContext).setIndicatorsEnabled(true);

//        holder.mImageView.setImageResource(R.drawable.f5);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }


}
