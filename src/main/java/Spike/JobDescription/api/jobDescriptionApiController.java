package Spike.JobDescription.api;

import Spike.JobDescription.api.domain.JobDescriptionDto;
import Spike.JobDescription.service.JobDescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class jobDescriptionApiController {


    private final JobDescriptionService jobDescriptionService;

    // 조회
    @GetMapping("/jobs/api/{jobId}/jobDescriptions")
    public ResponseEntity<List<JobDescriptionDto>> jobDescriptions(@PathVariable Long jobId) {
        List<JobDescriptionDto> JDDtos = jobDescriptionService.showAll(jobId);
        return ResponseEntity.status(HttpStatus.OK).body(JDDtos);
    }
    // 생성
    @PostMapping("/jobs/api/{jobId}/jobDescriptions")
    public ResponseEntity<JobDescriptionDto> create(@PathVariable Long jobId, @RequestBody JobDescriptionDto dto) {
        JobDescriptionDto jobDescriptionDto = jobDescriptionService.create(dto, jobId);
        return ResponseEntity.status(HttpStatus.OK).body(jobDescriptionDto);
    }

    // 수정
    // 삭제

}
