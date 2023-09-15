package AppTeam.AppTeamApp.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CovidService {
    public String covidBanner(String date, String region) throws IOException {
        URL obj2 = new URL(String.format("https://api.coronavirus.data.gov.uk/generic/log_banners/%s/Daily%%20Summary/overview/%s?page=1", date, region));
        StringBuffer covid = new StringBuffer();

        try {
            HttpURLConnection con2 = (HttpURLConnection) obj2.openConnection();
            con2.setRequestMethod("GET");
            int responseCode2 = con2.getResponseCode();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
            String inputLine2;

            while ((inputLine2 = in2.readLine()) != null) {
                covid.append(inputLine2);
            }
            in2.close();
        } catch(IOException e){
            covid.append("\nERROR: Could not access Covid Data.\n").append(e.getMessage());
        }

        return covid.toString();
    }
    public String covidMetrics(String date) throws IOException {
        URL obj2 = new URL(String.format("https://api.coronavirus.data.gov.uk/v2/data?areaType=overview&metric=cumCasesByPublishDateRate&metric=cumDailyNsoDeathsByDeathDate&metric=cumPeopleVaccinatedFirstDoseByPublishDate&format=json&release=%s", date));
        StringBuffer covid = new StringBuffer();
        try {
            HttpURLConnection con2 = (HttpURLConnection) obj2.openConnection();
            con2.setRequestMethod("GET");
            int responseCode2 = con2.getResponseCode();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
            String inputLine2;

            while ((inputLine2 = in2.readLine()) != null) {
                covid.append(inputLine2);
            }
            in2.close();
        } catch(IOException e){
            covid.append("\nERROR: Could not access Covid Data.\n").append(e.getMessage());
        }

        return covid.substring(0, 1501);

    }
}
