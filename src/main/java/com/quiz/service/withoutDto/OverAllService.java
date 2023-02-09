package com.quiz.service.withoutDto;

import java.util.Set;

import com.quiz.entity.Overall;
import com.quiz.entity.QuestionLevel;

public interface OverAllService extends CommonService<Overall>{

    // public Overall addOverall(Overall result);
	
	// public Overall updateOverall(Overall result);
	
	// public Set<Overall> getOveralls();
	
	
	// public Overall getOverall(Long resultId);
	
	// public void deleteOverall(Long resultId);


    // public Set<Overall> getOverallsOfUser(String username);
	
	// public Set<Overall> getOverallsOfQuestionLevel(QuestionLevel questionLevel);
	public Overall addOverall(Overall overall);
	
	public Overall updateOverall(Overall overall);
	
	public Set<Overall> getOveralls();
	
	public Overall getOverall(Long overallId);
	
	public void deleteOverall(Long overallId);
	
	public Set<Overall> getOverallsOfUser(String username);
	
	public Set<Overall> getOverallsOfQuestionLevel(QuestionLevel questionLevel);
	
}
