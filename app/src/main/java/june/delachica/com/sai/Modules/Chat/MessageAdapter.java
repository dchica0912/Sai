package june.delachica.com.sai.Modules.Chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import june.delachica.com.sai.R;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textMessage);
        }
    }

    List<ResponseMessage> mResponseMessages;

    public MessageAdapter(List<ResponseMessage> responseMessages) {
        mResponseMessages = responseMessages;
    }

    @Override
    public int getItemViewType(int position) {
        if (mResponseMessages.get(position).isUser())
            return R.layout.me_bubble;
        else
            return R.layout.bot_bubble;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.CustomViewHolder customViewHolder, int i) {
        customViewHolder.mTextView.setText(mResponseMessages.get(i).getTextMessage());
    }

    @Override
    public int getItemCount() {
        return mResponseMessages.size();
    }
}
