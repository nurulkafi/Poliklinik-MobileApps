package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelResepDetails;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataDetailsResep extends RecyclerView.Adapter<AdapterDataDetailsResep.HolderDetailsResep>{

    private Context ctx;
    private List<DataModelResepDetails> list;

    public AdapterDataDetailsResep(Context ctx, List<DataModelResepDetails> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataDetailsResep.HolderDetailsResep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_details, parent, false);
        AdapterDataDetailsResep.HolderDetailsResep holder = new AdapterDataDetailsResep.HolderDetailsResep(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataDetailsResep.HolderDetailsResep holder, int position) {
        DataModelResepDetails dm =list.get(position);
        holder.obat.setText(dm.getObat());
        holder.dosis.setText(dm.getDosis());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDetailsResep extends RecyclerView.ViewHolder{
        TextView obat;
        TextView dosis;


        public HolderDetailsResep(@NonNull View itemView) {
            super(itemView);
            obat = itemView.findViewById(R.id.TextObat);
            dosis = itemView.findViewById(R.id.ObatHarga);

        }

    }
}
