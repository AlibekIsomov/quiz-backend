package com.quiz.controller.withDto;


import com.quiz.dto.QuestionDTO;
import com.quiz.entity.Question;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.witDto.CommonServiceDto;
import com.quiz.service.withoutDto.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/questions")
public class QuestionDtoController extends AbstractDTOController<Question, QuestionDTO> {


    public QuestionDtoController(CommonServiceDto<Question, QuestionDTO> service) {
        super(service);
    }
}