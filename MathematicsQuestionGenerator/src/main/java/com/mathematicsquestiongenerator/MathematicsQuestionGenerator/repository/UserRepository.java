package com.mathematicsquestiongenerator.MathematicsQuestionGenerator.repository;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
