package Spike.JobDescription.controller;

import Spike.JobDescription.api.domain.RequirementDto;
import Spike.JobDescription.dto.JobDto;
import Spike.JobDescription.dto.UserDto;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.entity.User;
import Spike.JobDescription.service.JobService;
import Spike.JobDescription.service.RequirementService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final RequirementService requirementService;

    // 상단 네비게이션 바에 로그인한 유저 데이터 처리를 위한 모델
    @ModelAttribute
    public void usernameToNavbar(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser, Model model) {
        model.addAttribute("username", loginUser.toEntity().getEmail());
        model.addAttribute("id", loginUser.toEntity().getId());
    }

    @GetMapping("")
    public String jobs(@PageableDefault(page = 1) Pageable pageable, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser) {
        User user = loginUser.toEntity();
        Page<JobDto> jobsPages = jobService.paging(pageable, user);

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
        model.addAttribute("user", user);
        return "jobs/JDList";
    }

    @GetMapping("/add")
    public String jobForm(Job job, Model model) {
        model.addAttribute("jobDto", new JobDto());
        return "jobs/addJob";
    }


    @PostMapping("/add")
    public String addJob(@ModelAttribute JobDto dto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser) {
        jobService.create(dto, loginUser.toEntity());
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String editJobForm(@PathVariable("id") Long id, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser) {

        if (jobService.isCorrectUser(id, loginUser.toEntity())) {
            JobDto JobDto = jobService.showJob(id);
            List<RequirementDto> requirementDtos = requirementService.showAll(id);
            if (JobDto != null) {
                model.addAttribute("jobDto", JobDto);
                model.addAttribute("requirements", requirementDtos);
                return "jobs/editJob";
            }
        }
        return "redirect:/jobs";
    }

    @GetMapping("/update/{id}")
    public String updateJobForm(@PathVariable("id") Long id, Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser) {
        if (jobService.isCorrectUser(id, loginUser.toEntity())) {
            JobDto jobDto = jobService.showJob(id);
            if (jobDto != null) {
                model.addAttribute("jobDto", jobDto);
                return "jobs/updateJob";
            }
        }
        return "redirect:/jobs";
    }

    @PostMapping("/update")
    public String updateJob(JobDto dto, Model model, UserDto userDto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser) {
        if (jobService.isCorrectUser(dto.getId(), loginUser.toEntity())) {
            JobDto jobDto = jobService.patch(dto, userDto);
            if (jobDto != null) {
                model.addAttribute("jobDto", jobDto);
                return "redirect:/jobs/edit/" + jobDto.getId();
            }
        }
        return "redirect:/jobs";
    }

    @PostMapping("/delete")
    public String deleteJob(JobDto dto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserDto loginUser) {
        if (jobService.isCorrectUser(dto.getId(), loginUser.toEntity())) {
            if (jobService.delete(dto.getId())) {
                return "redirect:/jobs";
            }
        }
        return "jobs/editJob";

    }
}
