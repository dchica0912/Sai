package june.delachica.com.sai.Modules.Diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import june.delachica.com.sai.R;

public class DiaryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        RelativeLayout writeDiary = view.findViewById(R.id.writeDiary);
        writeDiary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(getActivity(),WriteDiary.class);
                startActivity(i);
            }
        });
        return view;
    }
}
