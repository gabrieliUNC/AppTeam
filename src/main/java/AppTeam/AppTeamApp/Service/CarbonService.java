package AppTeam.AppTeamApp.Service;

import io.micrometer.common.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CarbonService {
    public String batchCarbon(List<String> regionCodes, String from, String to) throws IOException {
        StringBuilder res = new StringBuilder();

        //Batch Processing of Regions
        for (String regionCode : regionCodes) {
            //Validation on Region Code
            if(StringUtils.isEmpty(regionCode)) continue;
            int check = 0;
            try {
                check = Integer.parseInt(regionCode);
            } catch (NumberFormatException e){
                res.append(String.format("\nERROR: Passed in an invalid Region Code: %s\n", regionCode));
                continue;
            }

            if(check < 1 || check > 17) {
                res.append(String.format("\nERROR: Passed in an invalid Region Code: %s\n", regionCode));
                continue;
            }

            //Hit Carbon Endpoint
            URL obj = new URL(String.format("https://api.carbonintensity.org.uk/regional/intensity/%s/%s/regionid/%s", from, to, regionCode));
            StringBuffer carbon = new StringBuffer();

            try {
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    carbon.append(inputLine);
                }
                in.close();
            } catch (IOException e){
                res.append("\nERROR: Could not access Carbon Data.\n").append(e.getMessage());
            }
            res.append(carbon);
        }
        return res.toString();
    }
    public String batchCarbonCurrent(List<String> regionCodes) throws IOException {
        StringBuilder res = new StringBuilder();

        //Batch Processing of Regions
        for (String regionCode : regionCodes) {
            //Validation on Region Code
            if(StringUtils.isEmpty(regionCode)) continue;

            int check = 0;
            try {
                check = Integer.parseInt(regionCode);
            } catch (NumberFormatException e){
                res.append(String.format("\nERROR: Passed in an invalid Region Code: %s\n", regionCode));
                continue;
            }

            if(check < 1 || check > 17) {
                res.append(String.format("\nERROR: Passed in an invalid Region Code: %s\n", regionCode));
                continue;
            }

            //Hit Carbon Endpoint
            StringBuffer carbon = new StringBuffer();
            URL obj = new URL(String.format("https://api.carbonintensity.org.uk/regional/regionid/%s", regionCode));
            try {
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    carbon.append(inputLine);
                }
                in.close();
                res.append(carbon);
            } catch (IOException e){
                res.append("\nERROR: Could not access Carbon Data.\n").append(e.getMessage());
            }
        }
        return res.toString();
    }
}
