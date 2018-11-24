package june.delachica.com.sai.Modules.Chat;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import june.delachica.com.sai.Main.MainActivity;
import june.delachica.com.sai.Modules.utilities.Responses;
import june.delachica.com.sai.Modules.utilities.SendRequestParameters;
import june.delachica.com.sai.Modules.utilities.WitAIDependencies;
import june.delachica.com.sai.R;

import static june.delachica.com.sai.Modules.utilities.WitAIDependencies.getServerResponse;

public class ChatbotFragment extends Fragment {
    private EditText main_userInputEditText;
    private RecyclerView main_recyclerView;
    private static List<ResponseMessage> mResponseMessages;
    private static MessageAdapter mMessageAdapter;
    private static final Responses responses = new Responses();
    private static String language, serverResponse;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        return inflater.inflate(R.layout.activity_sai_chat_bot, container, false);
        return inflater.inflate(R.layout.activity_sai_chat_bot, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main_userInputEditText = getActivity().findViewById(R.id.main_userInputEditText);
        main_recyclerView = getActivity().findViewById(R.id.main_recyclerView);

        mResponseMessages = new ArrayList<>();

        mResponseMessages.add(new ResponseMessage("Hi Spenz, how are you feeling today?", false));

        mMessageAdapter = new MessageAdapter(mResponseMessages);

        if (main_recyclerView != null) {
            main_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            main_recyclerView.setAdapter(mMessageAdapter);
        }

        if (main_userInputEditText != null) {
            main_userInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_SEND) {
                        ResponseMessage responseMessage = new ResponseMessage(main_userInputEditText.getText().toString(), true);
                        mResponseMessages.add(responseMessage);

                        mMessageAdapter.notifyDataSetChanged();

                        HashMap<String, String> versionQuery = new HashMap<>();

                        versionQuery.put("version", "20181102");
                        versionQuery.put("query", responseMessage.textMessage);

                        try {
                            URL mainURL = WitAIDependencies.buildURL(versionQuery);

                            DetectLanguage detectLanguage = new DetectLanguage();

                            detectLanguage.execute(mainURL);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            });
        }
    }

    public static void postAIResponse() {
        System.out.println("INSIDE postAIResponse");
        JsonElement jsonElement = new JsonParser().parse(serverResponse);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonObject entities = jsonObject.get("entities").getAsJsonObject();

        Set<Map.Entry<String, JsonElement>> elements = entities.entrySet();

        HashMap<String, String> sentence_properties = new HashMap<>();
        sentence_properties.put("language", language);

        for (Map.Entry<String, JsonElement> element: elements) {
            if (element.getKey().equals("mood_spanless")) {
                JsonObject mood_spanless = element.getValue().getAsJsonArray().get(0).getAsJsonObject();
                sentence_properties.put("mood_spanless", mood_spanless.get("value").getAsString());
            } else if (element.getKey().equals("mood")) {
                JsonObject mood = element.getValue().getAsJsonArray().get(0).getAsJsonObject();
                sentence_properties.put("mood", mood.get("value").getAsString());
            }
        }

        String aiResponse = responses.getResponse(sentence_properties);

        mResponseMessages.add(new ResponseMessage(aiResponse, false));
        mMessageAdapter.notifyDataSetChanged();
    }

    // Inner classes for Network-based operations

    class DetectLanguage extends AsyncTask<URL, String, String> {

        private URL theURL;
        @Override
        protected String doInBackground(URL... urls) {
            HttpURLConnection httpURLConnection = null;
            try {
                theURL = urls[0];
                httpURLConnection = WitAIDependencies.connectionMakerGET(urls[0], WitAIDependencies.getWitAI_languageDetect_accessToken());
            } catch (IOException e) {
                e.printStackTrace();
            }
            int responseCode = 0;
            try {
                responseCode = httpURLConnection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (responseCode != 200) {
                System.out.println("- Connection problem, try again");
                return null;
            } else {
                System.out.println("- Successfully reached language detection server...");

                JsonElement jElement = null;
                try {
                    jElement = new JsonParser().parse(getServerResponse(httpURLConnection.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JsonObject jObject = jElement.getAsJsonObject();

                JsonObject entities = jObject.getAsJsonObject("entities");
                JsonArray language = entities.getAsJsonArray("language");
                JsonObject languageObject = language.get(0).getAsJsonObject();
                String confidence = languageObject.get("confidence").toString();
                String value = languageObject.get("value").getAsString();
                String mainLang;
                switch (value) {
                    case "en":
                        mainLang = "ENGLISH";
                        break;
                    case "tl":
                        mainLang = "FILIPINO";
                        break;
                    default:
                        mainLang = "ENGLISH";
                        break;
                }

                return value;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            MainActivity.language = s;

            SendRequestParameters sendRequestParameters = new SendRequestParameters(theURL, s);

            SendRequest sendRequest = new SendRequest();

            sendRequest.execute(sendRequestParameters);
        }
    }

    class SendRequest extends AsyncTask<SendRequestParameters, Void, String> {

        @Override
        protected String doInBackground(SendRequestParameters... sendRequestParameters) {
            try {
                String accessTokenToBeUsed;
                if (sendRequestParameters[0].getLanguage() != null) {
                    switch (sendRequestParameters[0].getLanguage()) {
                        case "tl":
                            accessTokenToBeUsed = WitAIDependencies.getWitAI_SAI_PH_accessToken();
                            break;
                        default:
                            accessTokenToBeUsed =WitAIDependencies.getWitAI_SAI_EN_accessToken();
                            break;
                    }
                } else {
                    return null;
                }

                HttpURLConnection httpURLConnection = null;
                httpURLConnection = WitAIDependencies.connectionMakerGET(sendRequestParameters[0].getTheURL(), accessTokenToBeUsed);

                int responseCode = 0;
                responseCode = httpURLConnection.getResponseCode();


                if (responseCode != 200) {
                    System.out.println("- Connection problem, try again");
                    return null;
                } else {
                    System.out.println("- Successfully reached NLU server...");
                    return getServerResponse(httpURLConnection.getInputStream());

                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            MainActivity.serverResponse = s;
            MainActivity.postAIResponse();
        }
    }
}
