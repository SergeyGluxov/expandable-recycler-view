package com.thoughtbot.expandablerecyclerview.viewholders;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.R;
import com.thoughtbot.expandablerecyclerview.listeners.OnItemClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

/**
 * ViewHolder for {@link ExpandableGroup#items}
 */
public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected CheckedTextView textView;
    private OnItemClickListener listener;
    private Runnable mSetSelected = new Runnable() {
        @Override
        public void run() {
            if (textView != null) {
                textView.setSelected(true);
            }
        }
    };

    public ItemViewHolder(View itemView) {
        super(itemView);
        textView = (CheckedTextView)((FrameLayout) itemView).getChildAt(0);
        itemView.setOnClickListener(this);

        itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textView.postDelayed(mSetSelected, 300);
                } else {
                    textView.setSelected(false);
                    textView.removeCallbacks(mSetSelected);
                }
            }
        });
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    @Override
    public void onClick(View v) {
        if (listener != null && listener.onBeforeItemClick(getAdapterPosition(), textView.isChecked())) {
            return;
        }
        textView.setChecked(!textView.isChecked());
        if (listener != null) {
            listener.onItemClick(getAdapterPosition(), textView.isChecked());
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TextView getTextView() {
        return textView;
    }

    public void onBindViewHolder(int flatPos, boolean checked) {
        textView.setChecked(checked);
    }
}
