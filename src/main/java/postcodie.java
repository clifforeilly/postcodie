/**
 * Created by co17 on 07/03/2017.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class postcodie {

    public static void main (String[] args){

        try {
            String postcode = args[0];

            URL url = new URL("http://api.postcodes.io/postcodes/" + postcode);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();

            JSONObject json = new JSONObject(output);
            JSONArray jsona = new JSONArray(output);

            int h = 0;

            System.out.println(jsona.getString(2));

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

}
