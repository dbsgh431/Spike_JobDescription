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
    @GetMapping("/jobs/api/jobDescriptions/{jobId}")
    public ResponseEntity<List<JobDescriptionDto>> jobDescriptions(@PathVariable Long jobId) {
        List<JobDescriptionDto> JDDtos = jobDescriptionService.showAll(jobId);
        return ResponseEntity.status(HttpStatus.OK).body(JDDtos);
    }

    // 생성
    @PostMapping("/jobs/api/jobDescriptions/{jobId}")
    public ResponseEntity<JobDescriptionDto> create(@PathVariable Long jobId, @RequestBody JobDescriptionDto dto) {
        JobDescriptionDto jobDescriptionDto = jobDescriptionService.create(dto, jobId);
        return ResponseEntity.status(HttpStatus.OK).body(jobDescriptionDto);
    }

    // 수정
    @PatchMapping("/jobs/api/jobDescriptions/{jdId}")
    public ResponseEntity<JobDescriptionDto> update(@PathVariable Long jdId, @RequestBody JobDescriptionDto dto) {
        JobDescriptionDto descriptionDto = jobDescriptionService.patch(dto, jdId);
        return ResponseEntity.status(HttpStatus.OK).body(descriptionDto);
    }


    // 삭제
    @DeleteMapping("/jobs/api/jobDescriptions/{jdId}")
    public ResponseEntity<JobDescriptionDto> delete(@PathVariable Long jdId) {
        JobDescriptionDto removed = jobDescriptionService.remove(jdId);
        return ResponseEntity.status(HttpStatus.OK).body(removed);
    }
}
