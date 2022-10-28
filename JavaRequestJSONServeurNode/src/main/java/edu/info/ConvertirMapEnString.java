package edu.info;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ConvertirMapEnString {
    public static String getDonneesEnString(Map<String, String> donnees)
            throws UnsupportedEncodingException {
        StringBuilder resultat = new StringBuilder();

        for (Map.Entry<String, String> entry : donnees.entrySet()) {
            resultat.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            resultat.append("=");
            resultat.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            resultat.append("&");
        }

        String resultatString = resultat.toString();
        return resultatString.length() > 0
                ? resultatString.substring(0, resultatString.length() - 1)// Enlever & de la fin
                : resultatString;
    }
}
