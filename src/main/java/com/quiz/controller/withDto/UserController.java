package com.quiz.controller.withDto;
import com.quiz.dto.UserDTO;
import com.quiz.entity.User;
import com.quiz.service.witDto.CommonServiceDto;
import com.quiz.service.witDto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController extends AbstractDTOController<User, UserDTO> {

    @Autowired
    UserService userService;

    public UserController(CommonServiceDto<User, UserDTO> service) {
        super(service);
    }

    @RequestMapping("/search/{key}")
    public ResponseEntity<?> search(@PathVariable String key, Pageable pageable){
        return ResponseEntity.ok(userService.search(key,pageable));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

}
