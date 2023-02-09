package com.quiz.controller.withoutDto;

import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;
import com.quiz.service.withoutDto.CommonService;
import com.quiz.service.withoutDto.OverAllService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/overall")
public class OverallController extends AbstractController<Overall> {

    @Autowired
    private OverAllService overAllService;

    public OverallController(CommonService<Overall> service) {
        super(service);
    }

   // add overall
	@PostMapping("/")
	public ResponseEntity<Overall> add(@RequestBody Overall overall){
		return ResponseEntity.ok(this.overAllService.addOverall(overall));	
	}
	
	// update overall
	@PutMapping("/")
	public ResponseEntity<Overall> update(@RequestBody Overall overall){
		return ResponseEntity.ok(this.overAllService.updateOverall(overall));
	}
	
	// get all overalls
	@GetMapping("/all")
    public ResponseEntity<?> getAll(Pageable pageable){
        return ResponseEntity.ok(overAllService.getAll(pageable));
    }

	// get one overall
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(overAllService.getById(id));

    }
	
	
	// delete overall
	// @DeleteMapping("/get/{aId}")
	// public void delete(@PathVariable("aId") Long aId) {
	// 	this.overAllService.deleteOverall(aId);
	// }
	
	// get overalls of quiz
	@GetMapping("/level/get/{qId}")
	public ResponseEntity<?> getOverallsOfQuestionLevel(@PathVariable("qId") Long qId){
		QuestionLevel questionLevel = new QuestionLevel();
		questionLevel.setId(qId);
		return ResponseEntity.ok(this.overAllService.getOverallsOfQuestionLevel(questionLevel));
	}
	
	// get overalls of user
	@GetMapping("/user/get/{username}")
	public ResponseEntity<?> getoverallsofUser(@PathVariable("username") String username){
//		User user = new User();
//		user.setUsername(username);
		return ResponseEntity.ok(this.overAllService.getOverallsOfUser(username));
	}

}
