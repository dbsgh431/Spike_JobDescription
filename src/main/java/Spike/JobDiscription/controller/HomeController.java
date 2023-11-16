package Spike.JobDiscription.controller;


import Spike.JobDiscription.dto.UserDto;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }


    @PostMapping("/jobs/login")
    public String login(UserDto userDto, Model model, HttpSession session) {
        User user = userService.signIn(userDto);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/jobs";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/jobs/signUp")
    public String signUp(UserDto userDto) {
        userService.signUp(userDto);
        log.info("email={}", userDto.getEmail());
        log.info("password={}", userDto.getPassword());
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signUp";
    }
}