package hackathon.project.hackjamproject.controller;

import hackathon.project.hackjamproject.dto.user.UserCreationDTO;
import hackathon.project.hackjamproject.dto.user.UserDTO;
import hackathon.project.hackjamproject.entity.User;
import hackathon.project.hackjamproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/")
class UserController {
    private final UserService userService;

    @GetMapping("/user/{id}")
    User getById(@PathVariable Long id) {
       return userService.getUserById(id);
    }

    @GetMapping("/user/all")
    List<UserDTO> getAll() {
        return userService.getAllUsers();
    }

    @PutMapping("/user/update")
    User update(User user) {
        return userService.updateCurrentUser(user);
    }

    @PostMapping("/user")
    UserCreationDTO create(UserCreationDTO userCreationDTO) {
        return userService.createUser(userCreationDTO);
    }
}
