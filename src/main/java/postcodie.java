/**
 * Created by co17 on 07/03/2017.
 */

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class postcodie {

    static String webServiceURL = "";

    public static void main (String[] args){

        webServiceURL = "http://api.postcodes.io/postcodes/";
        String postcode = args[0];
        //postcode = "xxx";

        if(validatePostcode(postcode)){
            queryPostcode(postcode);
            nearestPostcode(postcode);
        }
    }

    public static void nearestPostcode(String postcode){
        try {

            URL url = new URL(webServiceURL + postcode + "/nearest");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            String jsont = "";
            while ((output = br.readLine()) != null) {
                jsont += output + "\n";
            }
            conn.disconnect();
            JSONObject obj = new JSONObject(jsont);
            int Status = obj.getInt("status");

            if (Status==200) {

                JSONArray jsona = obj.getJSONArray("result");

                int j = 0;
                for(int i = 0; i<jsona.length(); i++){
                    j++;
                    System.out.println("Nearest postcode " + j + ":" + jsona.getJSONObject(i).getString("postcode"));
                    System.out.println("Nearest postcode " + j + ", country:" + jsona.getJSONObject(i).getString("country"));
                    System.out.println("Nearest postcode " + j + ", region:" + jsona.getJSONObject(i).getString("region"));
                }
            }
            else{
                System.out.println("Postcode not found");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }


    public static Boolean validatePostcode(String postcode){
        Boolean output = false;
        try {

            URL url = new URL(webServiceURL + postcode + "/validate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output2;
            String jsont = "";
            while ((output2 = br.readLine()) != null) {
                jsont += output2 + "\n";
            }
            conn.disconnect();
            JSONObject obj = new JSONObject(jsont);
            int Status = obj.getInt("status");

            if (Status==200) {

                Boolean result = obj.getBoolean("result");
                if(result) {
                    System.out.println("Postcode is valid");
                    output = true;
                }
                else{
                    System.out.println("Postcode is invalid");
                }
            }
            else{
                System.out.println("Postcode not found");
            }

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return output;
    }


    public static void queryPostcode(String postcode){
        try {

            URL url = new URL(webServiceURL + postcode);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            String jsont = "";
            while ((output = br.readLine()) != null) {
                jsont += output + "\n";
            }
            conn.disconnect();
            JSONObject obj = new JSONObject(jsont);
            int Status = obj.getInt("status");

            if (Status==200) {

                String country = obj.getJSONObject("result").getString("country");
                System.out.println("Country is " + country);
                String region = obj.getJSONObject("result").getString("region");
                System.out.println("Region is " + region);
            }
            else{
                System.out.println("Postcode not found");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
