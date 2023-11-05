package Spike.JobDiscription.controller;

import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.repository.JobRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JobController {

    private final JobRepository jobRepository;

    @PostConstruct
    public void addDummy() {
        jobRepository.save(new Job(null, "토스", "기획", "naver.com", false));
    }

    @GetMapping("/jobs")
    public String jobs(Model model) {
        List<Job> jobs = jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "home";
    }

    @GetMapping("/jobs/add")
    public String addJobForm(Model model) {
        model.addAttribute("jobDto", new JobDto());
        return "addJob";
    }

    @PostMapping("/jobs/add")
    public String addJob(@ModelAttribute() JobDto dto, Model model) {
        Job job = dto.toEntity();
        Job save = jobRepository.save(job);
        model.addAttribute("job", save);
        return "redirect:/jobs";
    }
}
