package AppTeam.AppTeamApp.Controllers;

import AppTeam.AppTeamApp.DTO.CarbonCovidDTO;
import AppTeam.AppTeamApp.Service.AppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class AppController {
    private AppService appService = new AppService();

    @GetMapping("/data")
    public Object getData(@RequestBody CarbonCovidDTO carbonCovidDTO) throws IOException {
        try {
            return appService.callEndPoints(carbonCovidDTO);
        } catch (Exception e){
            return "Could not Produce Data for Carbon and Covid Endpoints.";
        }
    }
}
