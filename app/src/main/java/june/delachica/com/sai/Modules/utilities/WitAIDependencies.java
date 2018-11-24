package june.delachica.com.sai.Modules.utilities;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class WitAIDependencies {

    // Main components for accessing Wit AI
    private static final String witAI_scheme = "https";
    private static final String witAI_host = "api.wit.ai";
    private static final String witAI_path = "message";

    // Wit AI-based language detection feature
    private static final String witAI_languageDetect_accessToken = "B27YHFUU56KEBQGRNNVCW3YQFW3RU4HF";

    // Wit AI main NLU Layers
    private static final String witAI_SAI_EN_accessToken = "JPXZCEQXK5G4BBTACH6JGNGJFE3TXOFW";
    private static final String witAI_SAI_PH_accessToken = "TWZELGU3R25LGWOVVS53GSUC555SO47P";

    // Method for displaying dirty JSON text to pretty-printed text
    // @Param dirtyJson : String
    //    |-> the dirty String to be formatted
    public static String JSONDisplay(String dirtyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(dirtyJson);

        return gson.toJson(je);
    }

    // Method to build URL to reach Wit AI API
    // @Param urlElements : HashMap<String, String>
    //    |-> Map to determine the version to be used and query to be sent by the user
    public static URL buildURL(HashMap<String, String> urlElements) throws URISyntaxException, MalformedURLException {
//        URIBuilder uriBuilder = new URIBuilder()
//                .setScheme(witAI_scheme)
//                .setHost(witAI_host)
//                .setPath(witAI_path)
//                .addParameter("v", urlElements.get("version"))
//                .addParameter("q", urlElements.get("query"));
//
//        return uriBuilder.build().toURL();

        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder
                .scheme(witAI_scheme)
                .authority(witAI_host)
                .appendPath(witAI_path)
                .appendQueryParameter("v", urlElements.get("version"))
                .appendQueryParameter("q", urlElements.get("query"));
        return new URL(uriBuilder.build().toString());
    }

    // Method to get the JSON response of the API
    // @Param httpInputStream : InputStream
    //    |-> InputStream object that determines the source of the input
    public static String getServerResponse(InputStream httpInputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpInputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String serverResponse;

        while ((serverResponse = bufferedReader.readLine()) != null) {
            stringBuilder.append(serverResponse);
        }

        bufferedReader.close();

        return stringBuilder.toString();
    }

    // Method to detect the language of the given query
    // @Param witAI_URL : String
    //    |-> URL object to determine the network location of the api
    public static String detectLanguage(URL witAI_URL) throws IOException {
        HttpURLConnection httpURLConnection = connectionMakerGET(witAI_URL, witAI_languageDetect_accessToken);
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode != 200) {
            System.out.println("- Connection problem, try again");
            return null;
        } else {
            System.out.println("- Successfully reached language detection server...");

            JsonElement jElement = new JsonParser().parse(getServerResponse(httpURLConnection.getInputStream()));
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

            System.out.println("  Confidence level: " + confidence + "\n  Language detected: " + mainLang);

            return value;
        }
    }

    // Method to send the request formally to the main NLU layer
    // @Param witAI_URL : URL
    //    |-> URL object to determine the network location of the api
    // @Param language : String
    //    |-> String to determine the language (either en for English or tl for Tagalog)
    public static String sendRequest(URL witAI_URL, String language) throws IOException {
        String accessTokenToBeUsed;
        switch (language) {
            case "tl":
                accessTokenToBeUsed = witAI_SAI_PH_accessToken;
                break;
            default:
                accessTokenToBeUsed = witAI_SAI_EN_accessToken;
                break;
        }

        HttpURLConnection httpURLConnection = connectionMakerGET(witAI_URL, accessTokenToBeUsed);
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode != 200) {
            System.out.println("- Connection problem, try again");
            return null;
        } else {
            System.out.println("- Successfully reached NLU server...");
            return getServerResponse(httpURLConnection.getInputStream());
        }
    }

    // Method to return a new connection based on the URL
    // @Param theURL : URL
    //    |-> URL object to be used to open the connection via openConnection() method
    // @Param accessToken : String
    //    |-> String to determine which API call to make
    public static HttpURLConnection connectionMakerGET(URL theURL, String accessToken) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) theURL.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
        httpURLConnection.setRequestMethod("GET");

        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        return httpURLConnection;
    }

    public static String getWitAI_languageDetect_accessToken() {
        return witAI_languageDetect_accessToken;
    }

    public static String getWitAI_SAI_EN_accessToken() {
        return witAI_SAI_EN_accessToken;
    }

    public static String getWitAI_SAI_PH_accessToken() {
        return witAI_SAI_PH_accessToken;
    }
}
