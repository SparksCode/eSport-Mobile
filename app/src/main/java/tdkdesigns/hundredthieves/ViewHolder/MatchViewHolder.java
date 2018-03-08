package tdkdesigns.hundredthieves.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import tdkdesigns.hundredthieves.Interface.ItemClickListener;
import tdkdesigns.hundredthieves.R;

public class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtMatchOpponent, txtMatchDate;
    public ImageView imageOutcome;
    public LinearLayout matchLayout;

    private ItemClickListener itemClickListener;

    public MatchViewHolder(View itemView) {
        super(itemView);

        imageOutcome = itemView.findViewById(R.id.matchOutcome);
        txtMatchOpponent = itemView.findViewById(R.id.matchOpponent);
        txtMatchDate = itemView.findViewById(R.id.matchDate);
        matchLayout = itemView.findViewById(R.id.matchLayout);

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
