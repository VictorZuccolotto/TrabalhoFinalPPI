package me.dedin.TrabPPI.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.dedin.TrabPPI.db.model.User;
import me.dedin.TrabPPI.db.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public void createUser(User user){
        String pass = user.getSenha();
        user.setSenha(encoder.encode(pass));
        List<String> role = new ArrayList<String>();
        role.add("USERS");
        user.setRoles(role);
        repository.save(user);
    }
}