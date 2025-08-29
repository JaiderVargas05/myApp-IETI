package escuelaing.edu.co.myApp.service;

import escuelaing.edu.co.myApp.repository.user.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    User save(User user);

    Optional<User> findById(String id);

    List<User> all();

    void deleteById(String id);

    User update(User user, String userId);
}
