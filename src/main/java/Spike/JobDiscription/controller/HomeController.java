package Spike.JobDiscription.controller;


import Spike.JobDiscription.dto.UserDto;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.service.UserService;
import Spike.JobDiscription.web.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;



@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false)
                           User loginUser, Model model) {
        if (loginUser == null) {
            model.addAttribute("userDto", new UserDto());
            return "login";
        }
        return "redirect:/jobs";
    }


    @PostMapping("/jobs/login")
    public String login(UserDto userDto, Model model, HttpSession session) {
        User user = userService.signIn(userDto);
        if (user == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";

        }
        session.setAttribute(SessionConst.LOGIN_USER, user);
        model.addAttribute("userDto", userDto);
        return "redirect:/jobs";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SessionConst.LOGIN_USER);
        return "redirect:/";
    }

    @PostMapping("/jobs/signUp")
    public String signUp(UserDto userDto, Model model) {
        User user = userService.signUp(userDto);
        if (user != null) {
            return "redirect:/";
        } else {
            model.addAttribute("exist", "이미 존재하는 회원 이메일입니다.");
            return "signUp";
        }
    }

    @GetMapping("/jobs/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signUp";
    }
}