package com.example.chatrobot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> msgList;

    MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLlLeft, mLlRight;
        TextView mTvLeft, mTvRight;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLlLeft = itemView.findViewById(R.id.ll_left);
            mLlRight = itemView.findViewById(R.id.ll_right);
            mTvLeft = itemView.findViewById(R.id.tv_left_msg);
            mTvRight = itemView.findViewById(R.id.tv_right_msg);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_msg, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Msg msg = msgList.get(i);
        if (msg.getType() == Msg.TYPE_RECEIVED){
            viewHolder.mLlRight.setVisibility(View.GONE);
            viewHolder.mLlLeft.setVisibility(View.VISIBLE);
            viewHolder.mTvLeft.setText(msg.getContent());
        }else if (msg.getType() == Msg.TYPE_SENT){
            viewHolder.mLlLeft.setVisibility(View.GONE);
            viewHolder.mLlRight.setVisibility(View.VISIBLE);
            viewHolder.mTvRight.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
