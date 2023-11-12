package Spike.JobDiscription.controller;

import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.repository.JobRepository;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void addDummy() {
        jobRepository.save(new Job(null, "네이버", "기획", "https://www.naver.com/", false));
        jobRepository.save(new Job(null, "구글", "프론트엔드", "https://www.google.com/", false));
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
    public String addJob(@ModelAttribute() JobDto dto) {
        Job job = dto.toEntity();
        Job save = jobRepository.save(job);
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/edit/{id}")
    public String editJob(@PathVariable("id") Long id, Model model) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            model.addAttribute("job", job);
            return "editJob";
        }
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/update/{id}")
    public String updateJobForm(@PathVariable("id") Long id, Model model) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            model.addAttribute("jobDto", job);
            return "updateJob";
        }
        return "redirect:/jobs";
    }

    @PostMapping("/jobs/update")
    public String updateJob(JobDto dto, Model model) {
        Job job = dto.toEntity();
        Job updated = jobRepository.findById(job.getId()).orElse(null);
        if (updated != null) {
            jobRepository.save(job);
            model.addAttribute("job", job);
            return "redirect:/jobs/edit/" + job.getId();
        }
        return "redirect:/jobs";
    }

    @GetMapping("/jobs/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id) {
        Job target = jobRepository.findById(id).orElse(null);
        if (target != null) {
            jobRepository.delete(target);
            return "redirect:/jobs";
        }
        return "editJob";

    }
}
