package com.example.bookrecyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrecyclerview.Model.AuthorItem;
import com.example.bookrecyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class AuthorItemAdapter extends RecyclerView.Adapter<AuthorItemAdapter.ViewHolder> {
    private List<AuthorItem> authorItemList;
    public AuthorClickEvent authorClickEvent;
    
    public AuthorItemAdapter(List<AuthorItem> authorItemList) {
        this.authorItemList = authorItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View authorcard = layoutInflater.inflate(R.layout.authors_card, parent,false);
        ViewHolder viewHolder = new ViewHolder(authorcard);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AuthorItem authorItem = authorItemList.get(position);
        holder.imageView.setImageResource(authorItemList.get(position).getAuthorImg());
        holder.textView.setText(authorItemList.get(position).getAuthorName());
        holder.authCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorClickEvent.onAuthorClick(authorItem, position);
            }
        });
    }
    

    @Override
    public int getItemCount() {
        return authorItemList.size();
    }

    public void setOnAuthorClickListner(AuthorClickEvent authorClickEvent) {
        this.authorClickEvent = authorClickEvent;
    }

    public void Update(ArrayList<AuthorItem> authorItemList) {
        this.authorItemList = authorItemList;
        notifyDataSetChanged();
    }

    public interface AuthorClickEvent {
        void onAuthorClick(AuthorItem authorItem, int pos);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout linearLayout;
        public CardView authCard;
        
        public ViewHolder(View itemView){
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.idAuthorImg);
            this.textView = (TextView) itemView.findViewById(R.id.idAuthorName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout2);
            authCard = (CardView) itemView.findViewById(R.id.authCard);
        }
    }
}
