package june.delachica.com.sai.Modules.Diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import june.delachica.com.sai.R;

public class DiaryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_diary, container, false);

        RelativeLayout writeDiary = view.findViewById(R.id.writeDiary2);
        writeDiary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(getActivity(),WriteDiary.class);
                startActivity(i);
            }



        });
        writeDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getActivity().getApplicationContext(), WriteDiary.class);
                startActivity(i);
            }
        });

        ImageView imageView = view.findViewById(R.id.listIcon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ListThoughts.class);
                startActivity(i);
            }
        });
        return view;


    }
}
