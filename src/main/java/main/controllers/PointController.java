package main.controllers;

import main.models.Point;
import main.models.PointDto;
import main.service.PointService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/main")
public class PointController {
    @Autowired
    private PointService pointService;
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/points")
    PointDto addPoint(@RequestBody Point point, Principal user){
        point.setUser(userService.findByLogin(user.getName()));
        System.out.println("Point " + point + " added!");
        return pointService.save(point).toPointDto();
    }

    @CrossOrigin
    @GetMapping
    Collection<PointDto> getPoints(Principal user){
        Collection<Point> collection = pointService.findByUser(userService.findByLogin(user.getName()));
        Collection<PointDto> newCol = new ArrayList<>();
        for (Point p:collection){
            newCol.add(p.toPointDto());
        }
        return newCol;
    }
}
