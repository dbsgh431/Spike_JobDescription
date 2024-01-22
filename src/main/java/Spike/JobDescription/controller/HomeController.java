package Spike.JobDescription.controller;


import Spike.JobDescription.dto.UserDto;
import Spike.JobDescription.service.UserService;
import Spike.JobDescription.web.SessionConst;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/jobs";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        UserDto signedUserDto = userService.signIn(userDto);
        if (signedUserDto == null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login";

        }
        session.setAttribute(SessionConst.LOGIN_USER, signedUserDto);
        model.addAttribute("userDto", userDto);
        redirectAttributes.addAttribute("page", 1);
        return "redirect:/jobs";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(SessionConst.LOGIN_USER);
        return "redirect:/";
    }

    @GetMapping("/signUp")
    public String signUpForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Validated UserDto userDto, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            log.info("dto={}", userDto.getPassword());
            model.addAttribute("userDto", userDto);
            return "signUp";
        }

        UserDto signedUserDto = userService.signUp(userDto);
        if (signedUserDto != null) {
            return "redirect:/jobs";
        } else {
            model.addAttribute("exist", "이미 존재하는 회원 이메일입니다.");
            return "signUp";
        }
    }


    @PostMapping("/withdraw")
    public String withdraw(@RequestParam(name = "id") Long id, HttpSession session) {
        session.removeAttribute(SessionConst.LOGIN_USER);
        userService.withdraw(id);
        return "redirect:/jobs";
    }
}