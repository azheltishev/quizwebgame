package lv.progmeistars.webquiz.quiz;

import lombok.Data;

import java.util.List;

@Data
public class GameState {

    private List<WordTranslationTypeData> words;
    private WordTranslationTypeData currentWord;

}
