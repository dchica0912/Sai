package june.delachica.com.sai.Modules.utilities;

import java.net.URL;

public class SendRequestParameters {
    private URL theURL;
    private String language;

    public SendRequestParameters(URL theURL, String language) {
        this.theURL = theURL;
        this.language = language;
    }

    public URL getTheURL() {
        return theURL;
    }

    public void setTheURL(URL theURL) {
        this.theURL = theURL;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
