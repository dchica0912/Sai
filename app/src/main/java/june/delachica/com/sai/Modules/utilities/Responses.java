package june.delachica.com.sai.Modules.utilities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Responses {
    private Map<String, String[]> en_responses = new HashMap<>();
    private Map<String, String[]> ph_responses = new HashMap<>();

    public Responses() {

//        try {
//            loadEnglishResponses();
//            loadPhilippineResponses();

//            Iterator iterator = en_responses.entrySet().iterator();
//
//            while (iterator.hasNext()) {
//                Map.Entry keyValue = (Map.Entry) iterator.next();
//                System.out.println(keyValue.getKey());
//
//                for (String stringValue : (String[]) keyValue.getValue()) {
//                    System.out.println(stringValue);
//                }
//            }
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void loadEnglishResponses() throws ParserConfigurationException, IOException, SAXException {
        File en_responses_xml = new File("xml/en_responses.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(en_responses_xml);

        // eliminate dirty XML
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("mood");

        putResponses(nodeList, "en");
    }

    private void loadPhilippineResponses() throws ParserConfigurationException, IOException, SAXException {
        File ph_responses_xml = new File("xml/ph_responses.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(ph_responses_xml);

        // eliminate dirty XML
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("mood");

        putResponses(nodeList, "ph");
    }

    private void putResponses(NodeList nodeList, String language) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String moodType = element.getAttribute("type");

                ArrayList<String> responsesArrayList = new ArrayList<>();

                responsesArrayList.add(element.getElementsByTagName("response").item(0).getTextContent());

                String[] responsesArray = responsesArrayList.toArray(new String[0]);

                switch (language) {
                    case "en":
                        en_responses.put(moodType, responsesArray);
                        break;
                    case "ph":
                        ph_responses.put(moodType, responsesArray);
                        break;
                }
            }
        }
    }

    public String getResponse(HashMap requestProperties) {
        switch ((String) requestProperties.get("language")) {
            case "en":

                // TODO: return english responses
                return "english response";
            case "tl":

                // TODO: return filipino responses
                return "filipino response";
            default:
                return null;
        }
    }
}
