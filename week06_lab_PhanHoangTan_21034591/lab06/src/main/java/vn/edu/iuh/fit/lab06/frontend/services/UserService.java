package vn.edu.iuh.fit.lab06.frontend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab06.frontend.repositories.UserRepository;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
