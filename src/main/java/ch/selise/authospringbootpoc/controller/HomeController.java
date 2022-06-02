package ch.selise.authospringbootpoc.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, final Authentication authentication) {
        if (authentication != null && authentication instanceof TestingAuthenticationToken) {
            TestingAuthenticationToken token = (TestingAuthenticationToken) authentication;
            DecodedJWT jwt = JWT.decode(token.getCredentials().toString());
            String email = jwt.getClaims().get("email").asString();
            model.addAttribute("email", email);
        }
        return "index";
    }

}