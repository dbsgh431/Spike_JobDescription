package Spike.JobDiscription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobController {

    @GetMapping("/jobs")
    public String jobs() {
        return "jobs";
    }
    @GetMapping("/jobs/add")
    public String addJobForm() {
        return "addJob";
    }
    @PostMapping("/jobs/add")
    public String addJob() {
        return "jobs";
    }
}
