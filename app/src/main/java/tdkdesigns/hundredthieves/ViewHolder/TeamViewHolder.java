package tdkdesigns.hundredthieves.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tdkdesigns.hundredthieves.Interface.ItemClickListener;
import tdkdesigns.hundredthieves.R;

public class TeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView txtTeamName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public TeamViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.team_image);
        txtTeamName = itemView.findViewById(R.id.team_name);

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
