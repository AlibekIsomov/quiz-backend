package com.quiz.service.withoutDTO;

import java.util.List;

import com.quiz.dto.OverallDTO;
import com.quiz.entity.Overall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OverAllService extends CommonService<Overall> {

	public Overall addOverallWithUser(Overall overall);

	public OverallDTO getOverallDTO(Long id);

	public Page<OverallDTO> getAllDTO(Pageable pageable);


	List<Overall> getOverallsOfQuestionLevel(Long id);

	Overall updateOverall(Overall overall);
}
