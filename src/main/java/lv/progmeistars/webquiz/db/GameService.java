package lv.progmeistars.webquiz.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public Long newGame(String username) {
        GameEntity entity = new GameEntity();

        entity.setUsername(username);
        entity.setScore(0);
        entity.setAnsweredQuestions(0);
        entity.setTotalQuestions(5);

        GameEntity savedEntity = gameRepository.save(entity);

        return savedEntity.getId();
    }

    public void increaseAnsweredQuestions(Long gameId, boolean resultIsCorrect) {
        GameEntity gameEntity = getGameEntity(gameId);

        gameEntity.setAnsweredQuestions(gameEntity.getAnsweredQuestions() + 1);
        if (resultIsCorrect) {
            gameEntity.setScore(gameEntity.getScore() + 1);
        }

        gameRepository.save(gameEntity);
    }

    public boolean hasNextWord(Long gameId) {
        GameEntity gameEntity = getGameEntity(gameId);

        return gameEntity.getAnsweredQuestions() < gameEntity.getTotalQuestions();
    }

    public double getScore(Long gameId) {
        GameEntity gameEntity = getGameEntity(gameId);

        return gameEntity.getScore() / gameEntity.getTotalQuestions();
    }

    private GameEntity getGameEntity(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow();
    }
}
