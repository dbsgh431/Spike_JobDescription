package Spike.JobDescription.controller;

import Spike.JobDescription.dto.JobDto;
import Spike.JobDescription.dto.UserDto;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.entity.User;
import Spike.JobDescription.service.JobService;
import Spike.JobDescription.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    // 상단 네비게이션 바에 로그인한 유저 데이터 처리를 위한 모델
    @ModelAttribute
    public void usernameToNavbar(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {
        model.addAttribute("username", loginUser.getEmail());
    }

    @GetMapping("")
    public String jobs(@PageableDefault(page = 1) Pageable pageable, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        Page<JobDto> jobsPages = jobService.paging(pageable, loginUser);

        /**
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), jobsPages.getTotalPages());

        model.addAttribute("jobsPages", jobsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("user", loginUser);
        return "jobs/JDList";
    }

    @GetMapping("/add")
    public String jobForm(Job job, Model model) {
        model.addAttribute("jobDto", new JobDto());
        return "jobs/addJob";
    }


    @PostMapping("/add")
    public String addJob(@ModelAttribute JobDto dto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        jobService.create(dto, loginUser);
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String editJobForm(@PathVariable("id") Long id, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {

        if (jobService.isCorrectUser(id, loginUser)) {
            JobDto JobDto = jobService.showJob(id);
            if (JobDto != null) {
                model.addAttribute("jobDto", JobDto);
                return "jobs/editJob";
            }
        }
        return "redirect:/jobs";
    }

    @GetMapping("/update/{id}")
    public String updateJobForm(@PathVariable("id") Long id, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        if (jobService.isCorrectUser(id, loginUser)) {
            JobDto jobDto = jobService.showJob(id);
            if (jobDto != null) {
                model.addAttribute("jobDto", jobDto);
                return "jobs/updateJob";
            }
        }
        return "redirect:/jobs";
    }

    @PostMapping("/update")
    public String updateJob(JobDto dto, Model model, UserDto userDto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        if (jobService.isCorrectUser(dto.getId(), loginUser)) {
            JobDto jobDto = jobService.patch(dto, userDto);
            if (jobDto != null) {
                model.addAttribute("jobDto", jobDto);
                return "redirect:/jobs/edit/" + jobDto.getId();
            }
        }
        return "redirect:/jobs";
    }

    @PostMapping("/delete")
    public String deleteJob(JobDto dto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        if (jobService.isCorrectUser(dto.getId(), loginUser)) {
            if (jobService.delete(dto.getId())) {
                return "redirect:/jobs";
            }
        }
        return "jobs/editJob";

    }
}
