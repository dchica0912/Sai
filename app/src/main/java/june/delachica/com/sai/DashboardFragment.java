package june.delachica.com.sai;

import android.content.Intent;
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
import android.widget.RelativeLayout;

public class DashboardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        CardView btnChatbotFragment = view.findViewById(R.id.talkButton);
        CardView btnTrackMood = view.findViewById(R.id.trackButton);
        CardView btnThoughts = view.findViewById(R.id.myThoughtsButton);
//        CardView btnChatbotFragment = view.findViewById(R.id.talkButton);
        btnChatbotFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ChatbotFragment());
                fr.commit();
            }
        });

        btnTrackMood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new MoodFragment());
                fr.commit();
            }
        });

        btnThoughts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new DiaryFragment());
                fr.commit();
            }
        });


        return view;
    }
}
