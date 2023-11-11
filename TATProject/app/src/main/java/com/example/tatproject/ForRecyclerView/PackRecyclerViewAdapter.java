package com.example.tatproject.ForRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tatproject.PackDetailActivity;
import com.example.tatproject.R;

import java.util.ArrayList;

public class PackRecyclerViewAdapter extends RecyclerView.Adapter<PackRecyclerViewAdapter.ViewHolder> {
    //
    private ArrayList<PackageSingleItem> items = null;
    private Context context;
    private final String username;

    public class ViewHolder extends RecyclerView.ViewHolder{
        // 객체 정의
        TextView textPackageID;
        TextView textTitle;
        TextView textPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textPackageID = itemView.findViewById(R.id.text_pack_id);
            textTitle = itemView.findViewById(R.id.text_pack_title);
            textPrice = itemView.findViewById(R.id.text_price);
        }
    }

    // 생성자
    public PackRecyclerViewAdapter(ArrayList<PackageSingleItem> list, Context context, String username){
        items = list;
        this.context = context;
        this.username = username;
    }

    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public PackRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.items_for_packrecyclerview, parent, false);
        PackRecyclerViewAdapter.ViewHolder vh = new PackRecyclerViewAdapter.ViewHolder(view);

        return vh;
    }

    // position 에 해당하는 데이터를 뷰홀더의 아이템 뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull PackRecyclerViewAdapter.ViewHolder holder, int position) {
        String packageID = items.get(position).packageID;
        String title = items.get(position).title;
        String price = items.get(position).price;

        holder.textPackageID.setText("\n" + packageID + "\n");
        holder.textTitle.setText("\n" + title + "\n");
        holder.textPrice.setText("\n" + price + "\n");

        holder.textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PackDetailActivity.class);
                intent.putExtra("packageID", packageID);
                intent.putExtra("username", username);

                context.startActivity(intent);
            }
        });
    }

    // 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 어댑터 아이템 수정
    public void setItems(ArrayList<PackageSingleItem> list){
        items = list;
        notifyDataSetChanged();
    }
}
