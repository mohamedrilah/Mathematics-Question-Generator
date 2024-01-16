package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.Topics;
import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topics, Integer> {
    Topics findTopicByTopicname(String topicname);
}
