package june.delachica.com.sai.Modules.Welcome;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import june.delachica.com.sai.Modules.User.Authentication.Login;
import june.delachica.com.sai.R;

public class WelcomeScreen extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mStartBtn;

    private int mCurrentPage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mStartBtn = (Button) findViewById(R.id.startBtn);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStarted();
            }
        });

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    public void getStarted() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[4];
        mDotsLayout.removeAllViews();

        for (int i=0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotsLayout.addView(mDots[i]);
        }

        if(mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.violet));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            mCurrentPage = i;

            if (i == mDots.length - 1) {
                mStartBtn.setEnabled(true);
                mStartBtn.setVisibility(View.VISIBLE);

                mStartBtn.setText("Get started");

            } else {
                mStartBtn.setEnabled(false);
                mStartBtn.setVisibility(View.INVISIBLE);

                mStartBtn.setText("");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
