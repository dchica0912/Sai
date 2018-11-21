package june.delachica.com.sai.Modules.Mood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;


import june.delachica.com.sai.R;

public class MoodFragment extends Fragment {
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private SeekBar mSeekBar;

    private ImageView mImageSeek;
    private ImageView mImageSeek2;
    private ImageView mImageSeek3;
    private ImageView mImageSeek4;
    private ImageView mImageSeek5;
    private int mCurrrentProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);

        final TextView mTextView = view.findViewById(R.id.textView);
        ProgressBar mProgressBar = view.findViewById(R.id.progressBar);
        SeekBar mSeekBar = view.findViewById(R.id.seekBar);
        final ImageView mImageSeek = view.findViewById(R.id.imageSeek);
        final ImageView mImageSeek2 = view.findViewById(R.id.imageSeek2);
        final ImageView mImageSeek3 = view.findViewById(R.id.imageSeek3);
        final ImageView mImageSeek4 = view.findViewById(R.id.imageSeek4);
        final ImageView mImageSeek5 = view.findViewById(R.id.imageSeek5);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mTextView.setText("" + progress + "%");

                mCurrrentProgress = progress;

                if (progress>=0 && progress<=20) {
                    mImageSeek.setEnabled(false);
                    mImageSeek.setVisibility(View.INVISIBLE);
                    mImageSeek2.setEnabled(false);
                    mImageSeek2.setVisibility(View.INVISIBLE);
                    mImageSeek3.setEnabled(false);
                    mImageSeek3.setVisibility(View.INVISIBLE);
                    mImageSeek4.setEnabled(false);
                    mImageSeek4.setVisibility(View.INVISIBLE);
                    mImageSeek5.setEnabled(true);
                    mImageSeek5.setVisibility(View.VISIBLE);
                } else if (progress>=20 && progress<=40){
                    mImageSeek2.setEnabled(false);
                    mImageSeek2.setVisibility(View.INVISIBLE);
                    mImageSeek.setEnabled(false);
                    mImageSeek.setVisibility(View.INVISIBLE);
                    mImageSeek3.setEnabled(false);
                    mImageSeek3.setVisibility(View.INVISIBLE);
                    mImageSeek4.setEnabled(true);
                    mImageSeek4.setVisibility(View.VISIBLE);
                    mImageSeek5.setEnabled(false);
                    mImageSeek5.setVisibility(View.INVISIBLE);
                } else if (progress>=40 && progress<=60) {
                    mImageSeek3.setEnabled(true);
                    mImageSeek3.setVisibility(View.VISIBLE);
                    mImageSeek2.setEnabled(false);
                    mImageSeek2.setVisibility(View.INVISIBLE);
                    mImageSeek.setEnabled(false);
                    mImageSeek.setVisibility(View.INVISIBLE);
                    mImageSeek4.setEnabled(false);
                    mImageSeek4.setVisibility(View.INVISIBLE);
                    mImageSeek5.setEnabled(false);
                    mImageSeek5.setVisibility(View.INVISIBLE);
                } else if (progress>=60 && progress<=80) {
                    mImageSeek4.setEnabled(false);
                    mImageSeek4.setVisibility(View.INVISIBLE);
                    mImageSeek2.setEnabled(true);
                    mImageSeek2.setVisibility(View.VISIBLE);
                    mImageSeek3.setEnabled(false);
                    mImageSeek3.setVisibility(View.INVISIBLE);
                    mImageSeek.setEnabled(false);
                    mImageSeek.setVisibility(View.INVISIBLE);
                    mImageSeek5.setEnabled(false);
                    mImageSeek5.setVisibility(View.INVISIBLE);
                } else if (progress>=80 && progress<=100) {
                    mImageSeek5.setEnabled(false);
                    mImageSeek5.setVisibility(View.INVISIBLE);
                    mImageSeek2.setEnabled(false);
                    mImageSeek2.setVisibility(View.INVISIBLE);
                    mImageSeek3.setEnabled(false);
                    mImageSeek3.setVisibility(View.INVISIBLE);
                    mImageSeek4.setEnabled(false);
                    mImageSeek4.setVisibility(View.INVISIBLE);
                    mImageSeek.setEnabled(true);
                    mImageSeek.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }
}
