//package Spike.JobDiscription.controller;
//
//import Spike.JobDiscription.dto.JobDto;
//import Spike.JobDiscription.entity.Job;
//import Spike.JobDiscription.entity.User;
//import Spike.JobDiscription.repository.*;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//import static org.assertj.core.api.Assertions.*;
//
//
//
//class JobControllerTest {
//
//    private UserRepository userRepository = new UserRepositoryImpMemory();
//    private JobRepository jobRepository = new JobRepositoryImplMemory(userRepository);
//
//    @Autowired
//    public JobControllerTest(JobRepository jobRepository) {
//        this.jobRepository = jobRepository;
//    }
//
//    @Test
//    @Transactional
//    void 공고_전체조회() {
//        // 예상 데이터
//        User user1 = new User(1L, "dbsgh123@naver.com", "1234");
//        Job a = new Job(1L, "네이버", "기획", "https://www.naver.com/", false, user1);
//        Job b = new Job(2L, "구글", "프론트엔드", "https://www.google.com/", false, user1);
//        List<Job> expected = Arrays.asList(a, b);
//
//        // 실제 데이터
//        jobRepository.save(a);
//        jobRepository.save(b);
//        List<Job> actual = jobRepository.findAll();
//
//        // 검증 처리
//        assertThat(actual.toString()).isEqualTo(expected.toString());
//
//    }
//
//
//    @Test
//    @Transactional
//    void 공고_생성() {
//        // 예상 데이터
//        String companyName = "다음";
//        String role = "DB설계";
//        String url = "https://www.daum.net/";
//        Boolean isApply = false;
//
//        JobDto dto = new JobDto(null, companyName, role, url, isApply);
//        Job expected = new Job(null, companyName, role, url, isApply);
//
//        // 실제 데이터
//        Job actual = jobRepositoryImplJpa.save(dto.toEntity());
//        // 검증 검증 처리
//        assertThat(actual.getId()).isEqualTo(expected.getId());
//
//    }
//
//
//    @Test
//    @Transactional
//    void 공고id로_조회_실패() {
//        // 예상 데이터
//        Long id = 2L;
//        JobDto jobDto = new JobDto(null, "넥슨", "QA", "https://www.nexongames.co.kr/", false);
//        Job job = jobDto.toEntity();
//        Job expected = jobRepositoryImplJpa.save(job);
//
//        // 실제 데이터
//        Job actual = jobRepositoryImplJpa.findById(id).orElse(null);
//
//        // 검증 처리
//        assertThat(actual).isNull();
//    }
//
//
//    @Test
//    @Transactional
//    void 공고id로_조회_성공() {
//        // 예상 데이터
//        JobDto jobDto = new JobDto(null, "넥슨", "QA", "https://www.nexongames.co.kr/", false);
//        Job job = jobDto.toEntity();
//        Job expected = jobRepositoryImplJpa.save(job);
//
//        // 실제 데이터
//        Job actual = jobRepositoryImplJpa.findById(expected.getId()).orElse(null);
//
//        // 검증 처리
//        assertThat(actual.getId()).isEqualTo(expected.getId());
//    }
//
//
//    @Test
//    @Transactional
//    void 공고id로_수정_성공() {
//        // 예상 데이터
//        Job expected = new Job(null, "넥슨코리아", "QA", "https://www.nexongames.co.kr/", true);
//
//        // 실제 데이터
//        JobDto jobDto1 = new JobDto(null, "넥슨", "QA", "https://www.nexongames.co.kr/", false);
//        jobRepositoryImplJpa.save(jobDto1.toEntity());
//        JobDto updateDto = new JobDto(null, "넥슨코리아", "QA", "https://www.nexongames.co.kr/", true);
//        Job actual = jobRepositoryImplJpa.save(updateDto.toEntity());
//
//        // 검증 처리
//        assertThat(actual.getCompanyName()).isEqualTo(expected.getCompanyName());
//        assertThat(actual.getIsApply()).isEqualTo(expected.getIsApply());
//    }
//
//
//    @Test
//    @Transactional
//    void 공고id로_삭제_성공_() {
//        // 예상 데이터
//        JobDto jobDto = new JobDto(null, "넥슨", "QA", "https://www.nexongames.co.kr/", false);
//        Job saved = jobRepositoryImplJpa.save(jobDto.toEntity());
//
//        Job deleted = jobRepositoryImplJpa.save(new Job(null, "블리자드", "QA", "https://www.blizzard.com/ko-kr/", true));
//
//        jobRepositoryImplJpa.delete(deleted);
//        // 실제 데이터
//        Job actual = jobRepositoryImplJpa.findById(saved.getId()).orElse(null);
//        Job deletedActual = jobRepositoryImplJpa.findById(deleted.getId()).orElse(null);
//        // 검증 처리
//        assertThat(actual.toString()).isEqualTo(saved.toString());
//        assertThat(deletedActual).isNull();
//
//    }
//}