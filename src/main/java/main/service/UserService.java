package main.service;

import main.models.User;
import main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public Boolean save(User user){
        if (findByLogin(user.getLogin()) != null) return false;
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
    }

    public boolean getMatch(User user){
        User required = findByLogin(user.getLogin());
        return passwordEncoder.matches(user.getPassword(), required.getPassword());
    }
}
