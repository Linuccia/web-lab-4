package main.service;

import main.models.Point;
import main.models.User;
import main.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class PointService {
    @Autowired
    private PointRepository pointRepository;

    public Point save(Point point){
        return pointRepository.save(point);
    }

    @Transactional
    public Collection<Point> findByUser(User user){
        return pointRepository.findByUser(user);
    }
}
