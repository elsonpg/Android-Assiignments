package com.elson.expenser.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.elson.expenser.com.elson.expenser.models.IncomeCategoryInfo;

import java.util.List;

import expenser.elson.com.expensecalculator.R;


public class IncomeCategoryAdapter extends  RecyclerView.Adapter<IncomeCategoryAdapter.IncomeCategoryViewHolder> {

private List<IncomeCategoryInfo> Incomecategory;
OnItemClickListener mItemClickListener;
    public IncomeCategoryAdapter(List<IncomeCategoryInfo> Incomecategory)
    {
        this.Incomecategory = Incomecategory;
    }

    @Override
    public IncomeCategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new IncomeCategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncomeCategoryViewHolder incomeCategoryViewHolder, int i) {
        IncomeCategoryInfo ci = Incomecategory.get(i);
       // incomeCategoryViewHolder.vName.setText(ci.name);
       // incomeCategoryViewHolder.vSurname.setText(ci.surname);
       // incomeCategoryViewHolder.vEmail.setText(ci.email);
        incomeCategoryViewHolder.vTitle.setText(ci.name);
        if(ci.image != 0)
        {
            incomeCategoryViewHolder.vImage.setImageResource(ci.image);
        }
        else{
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(ci.name.substring(0,1), Color.RED);
            incomeCategoryViewHolder.vImage.setImageDrawable(drawable);
        }

    }

    @Override
    public int getItemCount() {
        return Incomecategory.size();
    }

    public class IncomeCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        protected ImageView vImage;

        public IncomeCategoryViewHolder(View v) {
            super(v);
           //// vName =  (TextView) v.findViewById(R.id.txtName);
           // vSurname = (TextView)  v.findViewById(R.id.txtSurname);
           // vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
            vImage = (ImageView) v.findViewById(R.id.text_image);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null)
            {
                mItemClickListener.onItemClick(v,getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;
    }
}
