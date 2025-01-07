//package com.example.SpringProject.User;
//
//import com.example.SpringProject.User.Dto.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "api/v1/user")
//public class UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public List<UserDto> getUsers(){
//        return userService.getUsers();
//    }
//
//    @PostMapping
//    public void saveUser(@RequestBody User user){
//       userService.saveUser(user);
//    }
//
//    @GetMapping("/{id}")
//    public UserDto getUserById(@PathVariable Long id){
//        System.out.println("Request received for ID: "+id);
//        return userService.getUserById(id);
//    }
//}
