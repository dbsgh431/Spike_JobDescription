package Spike.JobDiscription.controller;

import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.repository.JobRepository;
import Spike.JobDiscription.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobRepository jobRepository;
    private final JobService jobService;



    @GetMapping("/jobs")
    public String jobs(Model model) {
        List<Job> jobs = jobService.showAll();
        model.addAttribute("jobs", jobs);
        return "home";
    }

    @GetMapping("/jobs/add")
    public String addJobForm(Model model) {
        model.addAttribute("jobDto", new JobDto());
        return "addJob";
    }

    @PostMapping("/jobs/add")
    public String addJob(@ModelAttribute JobDto dto) {
        jobService.create(dto);
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/edit/{id}")
    public String editJob(@PathVariable("id") Long id, Model model) {
        Job job = jobService.showJob(id);

        if (job != null) {
            model.addAttribute("job", job);
            return "editJob";
        }
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/update/{id}")
    public String updateJobForm(@PathVariable("id") Long id, Model model) {
        Job job = jobService.showJob(id);
        if (job != null) {
            model.addAttribute("jobDto", job);
            return "updateJob";
        }
        return "redirect:/jobs";
    }

    @PostMapping("/jobs/update")
    public String updateJob(JobDto dto, Model model) {
        Job job = jobService.patch(dto);

        if (job != null) {
            model.addAttribute("job", job);
            return "redirect:/jobs/edit/" + job.getId();
        }
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id) {

        if (jobService.delete(id)) {
            return "redirect:/jobs";
        }
        return "editJob";

    }
}
