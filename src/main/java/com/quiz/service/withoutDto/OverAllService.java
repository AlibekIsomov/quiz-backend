package com.quiz.service.withoutDto;

import java.util.Set;

import com.quiz.dto.OverallDTO;
import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OverAllService extends CommonService<Overall>{


	
	public Overall updateOverall(Overall overall);
	public Overall addOverallWithUser(Overall overall);
	public Set<Overall> getOveralls();
	public OverallDTO getOverallDTO(Long id);


	public Page<OverallDTO> getAllDTO(Pageable pageable);
	
	public Set<Overall> getOverallsOfUser(String username);
	
	public Set<Overall> getOverallsOfQuestionLevel(QuestionLevel questionLevel);
	
}
