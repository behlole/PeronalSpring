package com.example.spingrest.Controllers;


import com.example.spingrest.Config.JwtTokenUtil;
import com.example.spingrest.Utilities.AuthRequest;
import com.example.spingrest.Utilities.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    private ResponseBody responseBody = new ResponseBody();

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            String token = this.jwtTokenUtil.generateToken(authRequest.getUsername());
            setResponse(token, "Token Generated Successfully", true);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception ex) {
            setResponse(null, ex.getMessage(), false);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
    }

    private void setResponse(String token, String message, Boolean success) {
        responseBody.setToken(token);
        responseBody.setSuccess(success);
        responseBody.setMessage(message);
    }


}
