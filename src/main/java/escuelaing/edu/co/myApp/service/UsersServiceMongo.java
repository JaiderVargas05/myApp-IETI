package escuelaing.edu.co.myApp.service;

import escuelaing.edu.co.myApp.repository.user.User;
import escuelaing.edu.co.myApp.repository.user.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Primary
public class UsersServiceMongo implements UsersService{
    Map<String, User> users = new HashMap<String, User>();
    private final UserRepository repo;

    public UsersServiceMongo(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public List<User> all() {
        return repo.findAll();
    }

    @Override
    public void deleteById(String id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        repo.deleteById(id);
    }

    @Override
    public User update(User user, String userId) {
        User current = repo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

         user.setId(userId);
         return repo.save(user);
    }
}
