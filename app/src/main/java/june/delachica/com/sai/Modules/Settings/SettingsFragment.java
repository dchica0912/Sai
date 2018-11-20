package june.delachica.com.sai.Modules.Settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import june.delachica.com.sai.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private Switch switch1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        switch1 = getActivity().findViewById(R.id.switch1);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.switch1){
            if (switch1.isChecked()){
                Toast.makeText(getContext(), "Hi", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
            }
        }
    }
}