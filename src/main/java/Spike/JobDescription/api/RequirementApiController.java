package Spike.JobDescription.api;

import Spike.JobDescription.api.domain.RequirementDto;
import Spike.JobDescription.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RequirementApiController {


    private final RequirementService requirementService;

    // 조회
    @GetMapping("/jobs/api/requirement/{jobId}")
    public ResponseEntity<List<RequirementDto>> jobDescriptions(@PathVariable Long jobId) {
        List<RequirementDto> dtos = requirementService.showAll(jobId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 생성
    @PostMapping("/jobs/api/requirement/{jobId}")
    public ResponseEntity<RequirementDto> create(@PathVariable Long jobId, @RequestBody RequirementDto dto) {
        RequirementDto created = requirementService.create(dto, jobId);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 수정
    @PatchMapping("/jobs/api/requirement/{requirementId}")
    public ResponseEntity<RequirementDto> update(@PathVariable Long requirementId, @RequestBody RequirementDto dto) {
        RequirementDto updated = requirementService.patch(dto, requirementId);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }


    // 삭제
    @DeleteMapping("/jobs/api/requirement/{requirementId}")
    public ResponseEntity<RequirementDto> delete(@PathVariable Long requirementId) {
        RequirementDto removed = requirementService.remove(requirementId);
        return ResponseEntity.status(HttpStatus.OK).body(removed);
    }
}
