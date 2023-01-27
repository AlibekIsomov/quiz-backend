package com.quiz.controller.withoutDto;

import com.quiz.entity.Overall;
import com.quiz.service.withoutDto.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/overall")
public class OverallController extends AbstractController<Overall>{
    public OverallController(CommonService<Overall> service) {
        super(service);
    }
}

