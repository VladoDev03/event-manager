//package com.example.SpringProject.User;
//
//import com.example.SpringProject.User.Dto.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import javax.swing.text.html.Option;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<UserDto> getUsers(){
//        return userRepository.findAll().stream().map(user -> new UserDto(
//                user.getUsername(),
//                user.getFirstName(),
//                user.getSecondName()
//        )).collect(Collectors.toList());
//    }
//
//    public void saveUser(User user){
//        userRepository.save(user);
//    }
//
//    public UserDto getUserById(Long id){
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null){
//            return new UserDto(user.getUsername(),user.getFirstName(),user.getSecondName());
//        }
//        return null;
//    }
//
//
//}
