package Spike.JobDiscription.controller;

import Spike.JobDiscription.dto.QuestionDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.Question;
import Spike.JobDiscription.service.JobService;
import Spike.JobDiscription.service.QuestionService;
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
public class QuestionController {

    private final QuestionService questionService;
    private final JobService jobService;


    @GetMapping("/{jobId}/questions")
    public String reviews(@PathVariable Long jobId, Model model) {
        List<Question> questions = questionService.showAll(jobId);
        Job job = jobService.showJob(jobId);

        model.addAttribute("questions", questions);
        model.addAttribute("job", job);
        return "questions/questionBoard";
    }

    @GetMapping("/{jobId}/questions/add")
    public String addForm(@PathVariable Long jobId, Model model) {
        Job job = jobService.showJob(jobId);
        model.addAttribute("questions", new Question());
        model.addAttribute("job", job);
        return "questions/addQuestion";
    }

    @PostMapping("/{jobId}/questions/add")
    public String add(@PathVariable Long jobId, QuestionDto dto, Model model) {
        Question question = questionService.create(dto, jobId);
        Job job = jobService.showJob(jobId);
        model.addAttribute("questions", new Question());
        model.addAttribute("job", job);
        return "redirect:/jobs/{jobId}/questions";
    }
}
