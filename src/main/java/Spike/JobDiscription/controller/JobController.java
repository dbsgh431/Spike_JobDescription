package Spike.JobDiscription.controller;

import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.dto.UserDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.service.JobService;
import Spike.JobDiscription.web.SessionConst;
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

    @ModelAttribute
    public void Username(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {
        model.addAttribute("username", loginUser.getEmail());
    }

    @GetMapping()
    public String jobs(Model model, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        List<Job> jobs = jobService.showAll(loginUser);
        model.addAttribute("jobs", jobs);
        model.addAttribute("user", loginUser);
        return "jobs/JDList";
    }

    @GetMapping("/add")
    public String JobForm(Job job, Model model) {
        model.addAttribute("jobDto", new JobDto());
        return "jobs/addJob";
    }


    @PostMapping("/add")
    public String addJob(@ModelAttribute JobDto dto, @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser) {
        jobService.create(dto, loginUser);

        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String editJobForm(@PathVariable("id") Long id, Model model) {
        Job job = jobService.showJob(id);

        if (job != null) {
            model.addAttribute("job", job);
            return "jobs/editJob";
        }
        return "redirect:/jobs";
    }

    @GetMapping("/update/{id}")
    public String updateJobForm(@PathVariable("id") Long id, Model model) {
        Job job = jobService.showJob(id);
        if (job != null) {
            model.addAttribute("jobDto", job);
            return "jobs/updateJob";
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
        return "jobs/editJob";

    }
}
