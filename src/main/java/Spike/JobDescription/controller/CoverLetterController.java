package Spike.JobDescription.controller;

import Spike.JobDescription.dto.CoverLetterDto;
import Spike.JobDescription.dto.JobDto;
import Spike.JobDescription.dto.UserDto;
import Spike.JobDescription.entity.CoverLetter;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.entity.User;
import Spike.JobDescription.service.CoverLetterService;
import Spike.JobDescription.service.JobService;
import Spike.JobDescription.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Slf4j
public class CoverLetterController {

    private final CoverLetterService coverLetterService;
    private final JobService jobService;

    // 상단 네비게이션 바에 로그인한 유저 데이터 처리를 위한 모델
    @ModelAttribute
    public void usernameToNavbar(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser, Model model) {
        model.addAttribute("username", loginUser.toEntity().getEmail());
        model.addAttribute("id", loginUser.toEntity().getId());
    }

    // 자기소개서 등록 시 해당 공고 정보 공통 처리 모델
    @ModelAttribute
    public void jobToView(@PathVariable Long jobId, Model model) {
        JobDto jobDto = jobService.showJob(jobId);
        model.addAttribute("jobDto", jobDto);
    }


    @GetMapping("/coverLetters/{jobId}")
    public String reviews(@PathVariable Long jobId, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUserDto) {
        if (coverLetterService.isCorrectUser(jobId, loginUserDto)) {
            List<CoverLetter> coverLetters = coverLetterService.showAll(jobId);
            model.addAttribute("coverLetters", coverLetters);
            return "coverLetters/ClList";
        }

        return "redirect:/jobs";
    }

    @GetMapping("/add/coverLetters/{jobId}")
    public String addForm(Model model) {
        model.addAttribute("coverLetters", new CoverLetter());
        return "coverLetters/addCL";
    }

    @PostMapping("/add/coverLetters/{jobId}")
    public String add(@PathVariable Long jobId, CoverLetterDto dto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUserDto) {
        if (coverLetterService.isCorrectUser(jobId, loginUserDto)) {
            coverLetterService.create(dto, jobId);
        }

        return "redirect:/jobs/coverLetters/{jobId}";
    }

    @GetMapping("/edit/coverLetters/{jobId}/{coverLettersId}")
    public String editForm(@PathVariable Long coverLettersId, @PathVariable Long jobId, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUserDto, CoverLetterDto dto) {
        if (coverLetterService.isCorrectUser(jobId, loginUserDto)) {
            CoverLetter coverLetter = coverLetterService.show(coverLettersId);
            model.addAttribute("coverLetterDto", new CoverLetterDto(coverLetter.getId(), coverLetter.getTitle(), coverLetter.getContent(), jobId));
            return "coverLetters/editCL";
        }
        return "redirect:/jobs/coverLetters/{jobId}";
    }


    @PostMapping("/edit/coverLetters/{jobId}")
    public String patch(@PathVariable Long jobId, CoverLetterDto coverLetterDto, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUserDto) {

        if (coverLetterService.isCorrectUser(jobId, loginUserDto)) {
            CoverLetter update = coverLetterService.update(jobId, coverLetterDto, coverLetterDto.getId());
            model.addAttribute("coverLetter", update);
            return "redirect:/jobs/coverLetters/{jobId}";
        }
        return "redirect:/jobs/coverLetters/{jobId}";
    }


    @PostMapping("/delete/coverLetters/{jobId}")
    public String delete(@ModelAttribute CoverLetterDto coverLetterDto, @PathVariable Long jobId, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUserDto) {
        if (coverLetterService.isCorrectUser(jobId, loginUserDto)) {
            coverLetterService.remove(coverLetterDto.getId());
        }
        return "redirect:/jobs/coverLetters/{jobId}";

    }
}
