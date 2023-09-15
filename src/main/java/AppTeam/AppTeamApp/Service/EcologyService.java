package AppTeam.AppTeamApp.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EcologyService {

    public String ecologyObservation() throws MalformedURLException {
        URL obj = new URL("https://environment.data.gov.uk/ecology/api/v1/observation-types?skip=0&take=250");
        StringBuffer ecology = new StringBuffer();

        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                ecology.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            ecology.append("Could not access Ecology Observation data.");
        }
        return ecology.toString();
    }

    public String ecologyTaxa() throws MalformedURLException {
        URL obj = new URL("https://environment.data.gov.uk/ecology/api/v1/taxa?skip=2&take=50");
        StringBuffer ecology = new StringBuffer();

        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                ecology.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            ecology.append("Could not access Ecology Taxa data.");
        }
        return ecology.toString();
    }

    public String ecologySpecies() throws MalformedURLException {
        URL obj = new URL("https://environment.data.gov.uk/ecology/api/v1/species?skip=0&take=250");
        StringBuffer ecology = new StringBuffer();

        try {
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                ecology.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            ecology.append("Could not access Ecology Species data.");
        }
        return ecology.toString();
    }
}
