package Spike.JobDiscription.service;

import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.repository.JobRepository;
import Spike.JobDiscription.repository.JobRepositoryImplMemory;
import Spike.JobDiscription.repository.UserRepository;
import Spike.JobDiscription.repository.UserRepositoryImpMemory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class JobServiceTest {
    private JobRepository jobRepository = new JobRepositoryImplMemory();

    @AfterEach
    void afterEach() {
        jobRepository.deleteAll();
    }

    @Test
    void 유저id에_대한_모든_공고조회() {
        // 예상 데이터
        User user1 = new User(1L, "dbsgh123@naver.com", "1234");
        User user2 = new User(2L, "dbsgh456@naver.com", "5678");

        Job job1 = new Job(null, "네이버", "기획", "https://www.naver.com/", false, LocalDate.of(2023, 12, 12), user1);
        Job job2 = new Job(null, "넥슨", "QA", "https://www.nexon.com/", false, LocalDate.of(2023, 12, 12),user2);
        Job job3 = new Job(null, "구글", "DB", "https://www.google.com/", false, LocalDate.of(2023, 12, 12), user2);

        Job expected1 = jobRepository.save(job1);
        Job expected2 = jobRepository.save(job2);
        Job expected3 = jobRepository.save(job3);

        List<Job> expectedJobsByUser = Arrays.asList(expected2, expected3);
        // 실제 데이터
        Long userid = 2L;
        List<Job> actualJobsByUser = jobRepository.findByUserId(userid);

        // 검증 처리
        assertThat(actualJobsByUser.toString()).isEqualTo(expectedJobsByUser.toString());
    }

    @Test
    void 공고_생성() {
        // 예상 데이터
        User user1 = new User(null, "dbsgh123@naver.com", "1234");
        Job expected1 = new Job(1L, "네이버", "기획", "https://www.naver.com/", false, LocalDate.of(2023, 12, 12), user1);
        Job expected2 = new Job(2L, "구글", "프론트엔드", "https://www.google.com/", false, LocalDate.of(2023, 12, 12), user1);


        List<Job> expectedJobs = Arrays.asList(expected1, expected2);

        // 실제 데이터
        Job actual1 = new Job(null, "네이버", "기획", "https://www.naver.com/", false,LocalDate.of(2023, 12, 12), user1);
        Job actual2 = new Job(null, "구글", "프론트엔드", "https://www.google.com/", false, LocalDate.of(2023, 12, 12), user1);
        Job save1 = jobRepository.save(actual1);
        Job save2 = jobRepository.save(actual2);
        List<Job> actualJobs = Arrays.asList(save1, save2);

        // 검증 처리
        assertThat(actualJobs.toString()).isEqualTo(expectedJobs.toString());
    }

    @Test
    void 게시글_id_로_조회() {
        // 예상 데이터
        User user1 = new User(null, "dbsgh123@naver.com", "1234");
        Job job = new Job(null, "네이버", "기획", "https://www.naver.com/", false,LocalDate.of(2023, 12, 12) ,user1);
        Job expected = jobRepository.save(job);
        System.out.println("expected = " + expected);
        // 실제 데이터
        Long id = 1L;
        Job actual = jobRepository.findById(id).orElse(null);

        // 검증 처리
        assertThat(actual.getId()).isEqualTo(expected.getId());
    }

}