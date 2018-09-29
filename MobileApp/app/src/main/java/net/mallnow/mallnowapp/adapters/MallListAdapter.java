package net.mallnow.mallnowapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import net.mallnow.mallnowapp.R;

import net.mallnow.mallnowapp.models.Mall;

import java.util.List;

public class MallListAdapter extends RecyclerView.Adapter<MallListAdapter.MallListHolder> {

   private Context mContext;
   private List<Mall> mListMall;
    RequestOptions option;

    public MallListAdapter(Context mContext, List<Mall> mListMall) {
        this.mContext = mContext;
        this.mListMall = mListMall;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MallListHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater layoutinflater =  LayoutInflater.from(mContext);
        view = layoutinflater.inflate(R.layout.mall_row_item,parent,false);
        return new MallListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MallListHolder mallListHolder, int i) {
    mallListHolder.tv_mallname.setText(mListMall.get(i).getMallName());
    mallListHolder.tv_address.setText(mListMall.get(i).getAddress());
    mallListHolder.tv_capacity.setText(mListMall.get(i).getCapacity());

    Glide.with(mContext).load(mListMall.get(i).getMobileIcon()).apply(option).into(mallListHolder.iv_mallicon);
    }

    @Override
    public int getItemCount() {
        return mListMall.size();
    }

    public static class MallListHolder extends RecyclerView.ViewHolder {

        TextView tv_mallname;
        TextView tv_address;
        TextView tv_capacity;
        ImageView iv_mallicon;

        public MallListHolder(@NonNull View itemView) {
            super(itemView);
            tv_mallname=itemView.findViewById(R.id.MallName);
            tv_address=itemView.findViewById(R.id.Address);
            tv_capacity=itemView.findViewById(R.id.Capacity);
            iv_mallicon=itemView.findViewById(R.id.thumbnail);

        }
    }
}
