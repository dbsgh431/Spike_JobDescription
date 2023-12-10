package Spike.JobDiscription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

    @PostMapping("/updateCharacterCount")
    @ResponseBody
    public String updateCharacterCount(@RequestBody String characterCount) {
        // 현재 입력 글자 수 리턴
        return String.valueOf(characterCount.length());
    }


}


