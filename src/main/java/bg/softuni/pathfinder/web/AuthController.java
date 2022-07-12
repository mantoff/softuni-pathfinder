package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserRegisterDTO;
import bg.softuni.pathfinder.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute
    public UserRegisterDTO initForm() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO userRegisterDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        System.out.println(userRegisterDTO);

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            return "redirect:/register";
        }

        this.authService.register(userRegisterDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
