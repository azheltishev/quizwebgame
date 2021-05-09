package lv.progmeistars.webquiz.db;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private double score = 0;
    private Integer answeredQuestions = 0;
    private Integer totalQuestions = 5;

}
