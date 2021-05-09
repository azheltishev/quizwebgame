package lv.progmeistars.webquiz.quiz;

import lombok.RequiredArgsConstructor;
import lv.progmeistars.webquiz.db.GameService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service // service == algorithms, db, files
@RequiredArgsConstructor
public class QuizGameService {

    private final CsvParser csvParser;
    private final GameService gameService;

    @Value("classpath:words.csv")
    private Resource wordsFile;
    private final Random random = new Random();

    private List<WordTranslationTypeData> words;
    private Map<Long, WordTranslationTypeData> gameState = new HashMap<>();

    @PostConstruct
    void init() throws IOException {
        words = csvParser.parseFile(wordsFile.getInputStream());
    }

    public Pair<Long, String> loadQuiz(String username) {
        Long gameId = gameService.newGame(username);
        return Pair.of(gameId, getNextWord(gameId));
    }

    public void checkWord(Long gameId, String translation) {

        WordTranslationTypeData wordTranslationTypeData = gameState.get(gameId);

        boolean resultIsCorrect = wordTranslationTypeData.getTranslationLanguage().toLowerCase().contains(translation.toLowerCase());
        gameService.increaseAnsweredQuestions(gameId, resultIsCorrect);
    }

    public boolean hasNextWord(Long gameId) {
        return gameService.hasNextWord(gameId);
    }

    public String getNextWord(Long gameId) {
        int randomIndex = random.nextInt(words.size());

        WordTranslationTypeData currentWord = words.get(randomIndex);
        gameState.put(gameId, currentWord);

        return currentWord.getSourceLanguage();
    }

    public double getScore(Long gameId) {
        return gameService.getScore(gameId);
    }

}
