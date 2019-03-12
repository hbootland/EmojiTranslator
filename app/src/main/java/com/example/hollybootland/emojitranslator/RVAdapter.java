package com.example.hollybootland.emojitranslator;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TranslationsViewHolder>{

    private LayoutInflater inflater;
    private ArrayList<Translations> mTranslations;
    Context context;
    private AdapterView.OnItemClickListener onItemClickListener;
    private ArrayList<Translations> mSavedTranslations;

    interface OnItemCheckListener {
        void onItemCheck(Translations t);
        void onItemUncheck(Translations t);
    }

    @NonNull
    private OnItemCheckListener onItemCheckListener;

    public static class TranslationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView inputLang;
        public TextView inputText;
        public TextView outputLang;
        public TextView outputText;
        CheckBox checkBox;
        CardView cardView;
        ItemClickListener icl;
        int pos;

        public TranslationsViewHolder(View itemView) {
            super(itemView);
            inputLang = itemView.findViewById(R.id.lang1);
            inputText = itemView.findViewById(R.id.inputText);
            outputLang =  itemView.findViewById(R.id.lang2);
            outputText =  itemView.findViewById(R.id.outputText);
            checkBox = itemView.findViewById(R.id.starButton);
            cardView = itemView.findViewById(R.id.cv);
//            itemView.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            int position = (int) v.getTag();
//            this.icl.onItemClick(getAdapterPosition(),v);
//        translationsViewHolder.checkBox.setChecked(true);
//        current.setChecked(true);
//        notifyDataSetChanged();
        }

        public void setItemClickListener(ItemClickListener ic) {
            this.icl = ic;
        }
    }

    public RVAdapter (Context context, ArrayList<Translations> translations ){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.mTranslations= translations;
        }


    @NonNull
    @Override
    public TranslationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.item,parent, false); // view object represents root of custom row
//        view.setOnClickListener(this);
        return new TranslationsViewHolder(view);
    }

    public interface RVClickListener {
        public void recyclerViewListClicked(View v, int position);
    }

//    @Override
//    public void onClick(View v) {
//        RVClickListener.onItemClick(getAdapterPosition(), v);
//    }
//



////




    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public void onBindViewHolder(@NonNull final TranslationsViewHolder translationsViewHolder, int i) {
        final Translations current = mTranslations.get(i);
        translationsViewHolder.inputLang.setText(current.getInLang());
        translationsViewHolder.inputText.setText(current.getInText());
        translationsViewHolder.outputLang.setText(current.getOutLang());
        translationsViewHolder.outputText.setText(current.getOutText());


//        translationsViewHolder.setItemClickListener(new ItemClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                //Check if checked or not
//                if(translationsViewHolder.checkBox.isChecked()){
//                    mSavedTranslations.add(mTranslations.get(position));
//                }else if(!translationsViewHolder.checkBox.isChecked()){
//
//                    mSavedTranslations.remove(mTranslations.get(position));
//                }
//            }
//        });


//        translationsViewHolder.cardView.setTag(i);


    }
//
    public void addItem(Translations translations, int index) {
        mTranslations.add(index, translations);
        notifyItemInserted(index);
    }
//
    public void deleteItem(int index) {
        mTranslations.remove(index);
        notifyItemRemoved(index);
    }

    public void updateData(ArrayList<Translations> translations) {
        mTranslations.clear();
        mTranslations.addAll(translations);
        notifyDataSetChanged();
    }

//    public int getAdapterPosition(){
//        return pos;
//    }

    @Override
    public int getItemCount() {

        return mTranslations.size();
    }



    public interface ItemClickListener {
         void onItemClick(int position, View v);
    }
}

