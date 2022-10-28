package edu.info;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;

//import org.json.JSONParser;

// <dependency>
//     <groupId>org.json</groupId>
//     <artifactId>json</artifactId>
//     <version>20220320</version>
// </dependency>

public class Connexion {
    private URL url;
    private HttpURLConnection conn;
    private static Connexion instance;
    
    // La visibilité du constructeur est privé pour intérdire
    // une instanciation directe en dehors la classe Connection
    private Connexion() {
    }

    // Singleton : avoir uneseule instance de la classe Connexion.
    public static Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }
       
    private void faireConnexion(String url) {
        try{
            this.url = new URL(url);
            conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
        }catch (Exception e){
            
        }
    }

    private String convertirReponseEnString() {
        String leString = "";
        try{
            int codeReponse = conn.getResponseCode();
            
            if (codeReponse != 200) {
                throw new RuntimeException("HttpcodeReponse: " + codeReponse);
            } else {
                Scanner scanner = new Scanner(this.url.openStream());
                // Les données reçues sont converties en String
                while (scanner.hasNext()) {
                    leString += scanner.nextLine();
                }
                // Fermer le scanner
                scanner.close();
            }
        }catch (Exception e){

        } 
        return leString;       
    }

    // https://docs.oracle.com/javaee/7/api/javax/json/JsonArray.html
    // https://docs.oracle.com/javaee/7/api/javax/json/JsonObject.html
    
    public JSONArray obtenirReponseEnJSONArray(String url) {
        String reponseEnString;
        JSONArray tabJSON;
        faireConnexion(url);
        reponseEnString = convertirReponseEnString();
        //System.out.println("String = "+reponseEnString);
        tabJSON = new JSONArray(reponseEnString);
        return tabJSON;
    }
}
