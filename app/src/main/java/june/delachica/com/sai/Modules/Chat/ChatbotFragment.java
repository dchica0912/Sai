package june.delachica.com.sai.Modules.Chat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import june.delachica.com.sai.R;

public class ChatbotFragment extends Fragment {
    TextView myTV;
    Typeface myFontRegular;
    Typeface myFontBold;
    Typeface myFontHeavy;
    Typeface myFontDemi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        myTV = getView().findViewById(R.id.time);
//        myFontRegular = Typeface.createFromAsset(getActivity().getAssets(),"fonts/._AvenirNextLTPro-Regular.otf");
//        myFontHeavy = Typeface.createFromAsset(getActivity().getAssets(),"fonts/._AvenirNextLTPro-Heavy.otf");
//        myFontBold = Typeface.createFromAsset(getActivity().getAssets(),"fonts/._AvenirNextLTPro-Bold.otf");
//        myFontDemi = Typeface.createFromAsset(getActivity().getAssets(),"fonts/._AvenirNextLTPro-Demi.otf");
//        myTV.setTypeface(myFontRegular);

        return inflater.inflate(R.layout.activity_sai_chat_bot, container, false);

    }
}
