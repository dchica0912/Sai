package june.delachica.com.sai.Modules.Mood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import io.ghyeok.stickyswitch.widget.StickySwitch;
import june.delachica.com.sai.R;

public class MoodFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mood, container, false);

//        StickySwitch stickySwitch = getActivity().findViewById(R.id.stick_switch);
//        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener(){
//            @Override
//            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String s){
//                Toast.makeText(getContext(),"You have selected" +s, Toast.LENGTH_SHORT).show();
//            }
//        });


        return view;
    }
}
