package Spike.JobDiscription.controller;

import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.dto.UserDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.repository.JobRepository;
import Spike.JobDiscription.service.JobService;
import Spike.JobDiscription.service.UserService;
import Spike.JobDiscription.web.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final UserService userService;


    @GetMapping()
    public String jobs(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        if (loginUser == null) {
            return "redirect:/";
        }
        List<Job> jobs = jobService.showAll(loginUser);
        model.addAttribute("jobs", jobs);
        model.addAttribute("user", loginUser);
        log.info(loginUser.toString());
        return "JDList";
    }

    @GetMapping("/add")
    public String addJobForm(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        model.addAttribute("jobDto", new JobDto());
        model.addAttribute("user", loginUser);
        return "addJob";
    }

    @PostMapping("/add")
    public String addJob(@ModelAttribute JobDto dto, @ModelAttribute("userDto") UserDto userDto) {
        jobService.create(dto, userDto);
        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable("id") Long id, Model model) {
        Job job = jobService.showJob(id);

        if (job != null) {
            model.addAttribute("job", job);
            return "editJob";
        }
        return "redirect:/jobs";
    }

    @GetMapping("/update/{id}")
    public String updateJobForm(@PathVariable("id") Long id, Model model) {
        Job job = jobService.showJob(id);
        if (job != null) {
            model.addAttribute("jobDto", job);
            return "updateJob";
        }
        return "redirect:/jobs";
    }

    @PostMapping("/update")
    public String updateJob(JobDto dto, Model model, UserDto userDto) {
        Job job = jobService.patch(dto, userDto);

        if (job != null) {
            model.addAttribute("job", job);
            return "redirect:/jobs/edit/" + job.getId();
        }
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id) {

        if (jobService.delete(id)) {
            return "redirect:/jobs";
        }
        return "editJob";

    }
}
