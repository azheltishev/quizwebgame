package lv.progmeistars.webquiz.quiz;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lv.progmeistars.webquiz.quiz.model.TranslationForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller // web interaction (http)
@RequestMapping("quiz")
@Slf4j
@AllArgsConstructor
public class QuizController {

//    @Autowired // dependency injection design pattern

    private final QuizGameService quizGameService;

    // replaced by lombok
//    public QuizController(QuizGameService quizGameService) { // bean
//        this.quizGameService = quizGameService;
//    }

    @GetMapping("/begin")
    public String quizBegin(@RequestParam(name = "username") String username, Map<String, Object> model) throws IOException {
        String nextWord = quizGameService.loadQuiz(username);
        model.put("word", nextWord);

        return "quiz";
    }

    @PostMapping("/next-word")
    public String quiz(@ModelAttribute TranslationForm translationForm, Map<String, Object> model) {
        String translation = translationForm.getTranslation();
        quizGameService.checkWord(translation);

        if (quizGameService.hasNextWord()) {
            String nextWord = quizGameService.getNextWord();
            model.put("word", nextWord);

            return "quiz";
        } else {
            double score = quizGameService.getScore();
            model.put("score", score * 100);

            return "finalscore";
        }
    }
}
