package lv.progmeistars.webquiz.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service // service == algorithms, db, files
@RequiredArgsConstructor
public class QuizGameService {

    private final CsvParser csvParser;
    private final WordParser wordParser;
    @Value("classpath:words.csv")
    private Resource wordsFile;
    private final Random random = new Random();

    private List<WordTranslationTypeData> words;
    private String username;
    private WordTranslationTypeData currentWord;
    private double score = 0;
    private Integer answeredQuestions = 0;
    private final Integer totalQuestions = 5;


    public String loadQuiz(String username) throws IOException {
        answeredQuestions = 0;
        score = 0;
        words = csvParser.parseFile(wordsFile.getInputStream());
        this.username = username;

        return getNextWord();
    }

    public void checkWord(String translation) {
        answeredQuestions++;
        if (currentWord.getTranslationLanguage().toLowerCase().contains(translation.toLowerCase())) {
            score++;
        }
    }

    public boolean hasNextWord() {
        return answeredQuestions < totalQuestions;
    }

    public String getNextWord() {
        int randomIndex = random.nextInt(words.size());

        currentWord = words.get(randomIndex);
        return currentWord.getSourceLanguage();
    }

    public double getScore() {
        return score / totalQuestions;
    }

}
