package com.leo.androidwidget.dialog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leo.androidwidget.R;
import com.leo.androidwidget.dialog.SmartDialog;
import com.leo.androidwidget.popup.PopupInterface;

public class ListSingleAdapter extends RecyclerView.Adapter<DialogViewHolder> {

  private final SmartDialog.Builder mBuilder;

  public ListSingleAdapter(SmartDialog.Builder builder) {
    mBuilder = builder;
  }

  @NonNull
  @Override
  public DialogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(mBuilder.getListItemLayout(),
        parent, false);
    DialogViewHolder viewHolder = new DialogViewHolder(view);
    view.setOnClickListener(v -> {
      int position = viewHolder.getAdapterPosition();
      SmartDialog dialog = mBuilder.getDialog();
      mBuilder.setSelectedIndex(position);
      mBuilder.getListCallback().onSelection(dialog, v, position);
      dialog.dismiss(PopupInterface.CLOSE_TYPE_POSITIVE);
    });
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull DialogViewHolder holder, int position) {
    ((TextView) holder.itemView.findViewById(R.id.item))
        .setText(mBuilder.getListItems().get(position));
  }

  @Override
  public int getItemCount() {
    return mBuilder.getListItems().size();
  }
}
