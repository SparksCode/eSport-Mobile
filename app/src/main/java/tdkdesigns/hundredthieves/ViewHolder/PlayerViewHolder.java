package tdkdesigns.hundredthieves.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tdkdesigns.hundredthieves.Interface.ItemClickListener;
import tdkdesigns.hundredthieves.R;

public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtPlayerName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public PlayerViewHolder(View itemView) {
        super(itemView);

        txtPlayerName = itemView.findViewById(R.id.player_name);
        imageView = itemView.findViewById(R.id.player_image);

        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}