package AppTeam.AppTeamApp.Service;

import AppTeam.AppTeamApp.DTO.CarbonCovidDTO;
import io.micrometer.common.util.StringUtils;
import javax.print.attribute.standard.Severity;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;


public class AppService {
    private CarbonService carbonService = new CarbonService();
    private CovidService covidService = new CovidService();
    private EcologyService ecologyService = new EcologyService();

    public String callEndPoints(CarbonCovidDTO carbonCovidDTO) throws IOException {
        //CarbonCovidDTO Transformation
        List<String> regionCodes = carbonCovidDTO.getRegionCodes();
        String from = carbonCovidDTO.getFrom();
        String to = carbonCovidDTO.getTo();
        String region = carbonCovidDTO.getRegion();
        CarbonCovidDTO.Ecology ecology = carbonCovidDTO.getEcology();

        //Params Validation
        if(StringUtils.isEmpty(from)){
            return "\n" + new InvalidParameterException(Severity.ERROR + ": Passed in empty date value.\n\n");
        }
        if(StringUtils.isEmpty(region)){
            return "\n" + new InvalidParameterException(Severity.ERROR + ": Passed in empty region value\n\n");
        }

        /*
        Carbon Endpoint with Methods
        */

        // Carbon Region Codes Validation
        String carbon = "\n" + new InvalidParameterException(Severity.ERROR + ": Passed in empty date value.\n\n");

        //Carbon for Single Date
        if(to == null || StringUtils.isEmpty(to)) carbon = carbonService.batchCarbonCurrent(regionCodes);

        //Carbon for Date Range
        else if(!regionCodes.isEmpty()) carbon = carbonService.batchCarbon(regionCodes, from, to);

        /*
         Covid Endpoint with Methods
        */

        //Covid Banner for Date and Region
        String covidBanner = covidService.covidBanner(from.substring(0, 10), region);

        // Covid Metrics for Date Range
        String covidMetrics = covidService.covidMetrics(from.substring(0, 10));

        /*
            Optional Ecology API
        * */

        //Ecology
        String ecologyData;
        //Parameter Validation
        if (ecology == null || StringUtils.isEmpty(String.valueOf(ecology))){
            ecologyData = "\n\nOpted not to show Ecology Data.\n";
        } else {
            switch (ecology) {
                case TAXA -> ecologyData = ecologyService.ecologyTaxa();
                case OBSERVATION -> ecologyData = ecologyService.ecologyObservation();
                case SPECIES -> ecologyData = ecologyService.ecologySpecies();
                default -> {
                    ecologyData = "";
                }
            }
        }

        return String.format("Carbon Data from %s to %s for %s regions!\n\n", from, to, regionCodes.size()) + carbon
                + String.format("\n\nCovid Data for %s for region: %s\n\n", from, region) + covidBanner +
                String.format("\n\nCovid Metrics up to date: %s\n\n", from.substring(0, 10)) + covidMetrics +
                String.format("\n\nEcology Data for %s: ", ecology) + ecologyData;
    }

}



