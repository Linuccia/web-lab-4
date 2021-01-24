package main.controllers;

import main.models.Point;
import main.models.PointDto;
import main.service.PointService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class PointController {
    @Autowired
    private PointService pointService;
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/points")
    PointDto addPoint(@RequestBody PointDto pointDto, @RequestHeader String login){
        Point point = pointDto.toPoint();
        point.setUser(userService.findByLogin(login));
        System.out.println("Point " + point + " added!");
        pointService.save(point);
        return pointDto;
    }

    @CrossOrigin
    @GetMapping("/")
    Collection<PointDto> getPoints(String login){
        Collection<Point> collection = pointService.findByUser(userService.findByLogin(login));
        Collection<PointDto> newCol = new ArrayList<>();
        for (Point p:collection){
            newCol.add(p.toPointDto());
        }
        return newCol;
    }
}
