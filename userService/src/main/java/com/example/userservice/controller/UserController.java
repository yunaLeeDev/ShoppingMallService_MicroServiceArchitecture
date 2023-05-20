package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {
    private UserService userService;
    private Environment env;

    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {

        return String.format("%s", env.getProperty("config.message"));
//        return String.format("It's Working in User Service on Part %s", request.getServerPort());
//        return String.format("It's Working in User Service on Part %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    /* SIGN UP */
    @PostMapping("/users/sign-up") // 사용자 정보 등록(회원가입)
    public String createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        return String.format("회원가입이 완료되었습니다. Id는 %s입니다", userDto.getUserId());
    }
    @GetMapping(value = "/users") // 전체 사용자 조회
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/users/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    /* DELETE */
    @DeleteMapping(value = "/users/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userService.deleteUser(userId);
        return String.format("%s님의 회원정보가 성공적으로 삭제되었습니다.", userId);
    }
}
