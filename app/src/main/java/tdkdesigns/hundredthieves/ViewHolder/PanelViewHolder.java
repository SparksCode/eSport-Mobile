package tdkdesigns.hundredthieves.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tdkdesigns.hundredthieves.Interface.ItemClickListener;
import tdkdesigns.hundredthieves.R;

/**
 * Created by Zach on 3/3/2018.
 */

public class PanelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView txtPanelName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public PanelViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.panel_image);
        txtPanelName = itemView.findViewById(R.id.panel_name);

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
