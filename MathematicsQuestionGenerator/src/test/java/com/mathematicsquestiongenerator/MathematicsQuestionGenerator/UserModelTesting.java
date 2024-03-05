package com.mathematicsquestiongenerator.MathematicsQuestionGenerator;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserModelTesting {

    @Test
    void testingUserCreation() {
        User user = new User();
        user.setFirstname("Mohamed");
        user.setSurname("Rilah");
        user.setUsername("mohamedrilah");
        user.setPassword("123456");
        user.setRole("Admin");

        assertEquals("Mohamed", user.getFirstname());
        assertEquals("Rilah", user.getSurname());
        assertEquals("mohamedrilah", user.getUsername());
        assertEquals("123456", user.getPassword());
        assertEquals("Admin", user.getRole());
    }
}
