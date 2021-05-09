package lv.progmeistars.webquiz.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestService {

    private final GameRepository gameRepository;

    @PostConstruct
    void init() {
        GameEntity entity = new GameEntity();

        entity.setUsername("John");
        entity.setScore(0.7);
        entity.setAnsweredQuestions(7);
        entity.setTotalQuestions(10);

        gameRepository.save(entity);
    }

}
