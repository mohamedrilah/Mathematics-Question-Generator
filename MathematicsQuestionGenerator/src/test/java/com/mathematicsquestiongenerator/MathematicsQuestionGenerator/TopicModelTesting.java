package com.mathematicsquestiongenerator.MathematicsQuestionGenerator;

import com.mathematicsquestiongenerator.MathematicsQuestionGenerator.model.Topics;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TopicModelTesting {

    @Test
    void testingTopicCreation() {
        Topics topic = new Topics();
        topic.setTopicname("Testing");
        topic.setDifficulty("Hard");

        String getTopicname = topic.getTopicname();
        String getDifficulty = topic.getDifficulty();

        assertEquals("Testing", getTopicname);
        assertEquals("Hard", getDifficulty);
    }
}
