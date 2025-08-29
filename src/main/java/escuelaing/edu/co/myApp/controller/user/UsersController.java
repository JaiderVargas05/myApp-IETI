package escuelaing.edu.co.myApp.controller.user;

import escuelaing.edu.co.myApp.repository.user.User;
import escuelaing.edu.co.myApp.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        if (user.getId() == null || user.getId().isBlank()) {
            user.setId(UUID.randomUUID().toString());
        }
        User saved = usersService.save(user);
        return ResponseEntity
                .created(URI.create("/users/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(usersService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        Optional<User> found = usersService.findById(id);
        return found.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {

        if (usersService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);
        User updated = usersService.update(user, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (usersService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}