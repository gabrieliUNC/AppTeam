package AppTeam.AppTeamApp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CarbonCovidDTO {
    private String from;
    private String to;
    private List<String> regionCodes;
    private String region;
    private Ecology ecology;
    public enum Ecology {
        OBSERVATION,
        TAXA,
        SPECIES
    }
}
