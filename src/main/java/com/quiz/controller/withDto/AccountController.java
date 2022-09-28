package com.quiz.controller.withDto;


import com.quiz.Security.JwtTokenUtil;
import com.quiz.Security.Token;
import com.quiz.Security.UserProvider;
import com.quiz.Security.UserSpecial;
import com.quiz.dto.UserDTO;
import com.quiz.entity.User;
import com.quiz.service.witDto.UserService;
import com.quiz.vm.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    UserProvider userProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/authenticate")
    public ResponseEntity<Token> login(@RequestBody UserSpecial userSpecial) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userSpecial.getUsername(), userSpecial.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        UserDetails userDetails = userProvider.loadUserByUsername(userSpecial.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails, true);
        return ResponseEntity.ok(new Token(token));

    }
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user) throws Exception {
        if (user.getId() != null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping()
    public ResponseEntity<UserDTO> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }



    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody UserVM vm) {


        if(userService.changePassword(vm)){

            return ResponseEntity.noContent().build();

        }
        return  ResponseEntity.badRequest().build();
    }
}
