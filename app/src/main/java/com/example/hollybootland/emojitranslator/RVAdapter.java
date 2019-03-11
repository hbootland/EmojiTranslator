package com.example.hollybootland.emojitranslator;

import android.content.Context;
import android.support.v7.widget.CardView;
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

    public static class TranslationsViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        public TextView inputLang;
        public TextView inputText;
        public TextView outputLang;
        public TextView outputText;
        CheckBox checkBox;

        public TranslationsViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            inputLang = itemView.findViewById(R.id.lang1);
            inputText = itemView.findViewById(R.id.inputText);
            outputLang =  itemView.findViewById(R.id.lang2);
            outputText =  itemView.findViewById(R.id.outputText);
            checkBox = itemView.findViewById(R.id.starButton);
//            itemView.setOnClickListener(this);
        }
    }

    public RVAdapter (Context context, ArrayList<Translations> translations ){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.mTranslations= translations;
        }


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
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @Override
    public void onBindViewHolder(TranslationsViewHolder translationsViewHolder, int i) {
        Translations current = mTranslations.get(i);

        translationsViewHolder.inputLang.setText(current.getInLang());
        translationsViewHolder.inputText.setText(current.getInText());
        translationsViewHolder.outputLang.setText(current.getOutLang());
        translationsViewHolder.outputText.setText(current.getOutText());
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

    @Override
    public int getItemCount() {

        return mTranslations.size();
    }

//    public interface MyClickListener {
//         void onItemClick(int position, View v);
//    }
}

