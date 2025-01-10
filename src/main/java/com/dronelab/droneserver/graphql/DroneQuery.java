
package com.dronelab.droneserver.graphql;

import com.dronelab.droneserver.dtos.request.DroneDTO;
import com.dronelab.droneserver.services.DroneService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DroneQuery {

    private DroneService droneService;

    public DroneQuery(DroneService droneService) {
        this.droneService = droneService;
    }

    @QueryMapping
    public List<DroneDTO> allDrones() {
        return droneService.getAllDrones();
    }
}
