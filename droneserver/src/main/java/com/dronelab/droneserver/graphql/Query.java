
package com.dronelab.droneserver.graphql;

//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dronelab.droneserver.entities.DroneRepo;
import com.dronelab.droneserver.pojo.Drone;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Ebube
 */

@Controller
@AllArgsConstructor
public class Query  {
    
    
    Drone droneDao;
    
    
    @QueryMapping
    public List<Drone> allDrones() {
        System.out.println("called ooo \n\n\n\n\n");
        return droneDao.getAllDrones();
    }
}
