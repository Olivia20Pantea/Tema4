package com.company;

public enum Questions {

    QUESTION_1("\t\n" + "During World War II, when did Germany attack France?", "1940", "1941", "1942", "1943", "1940"),
    QUESTION_2(" Which planet is the hottest in the solar system?", "Mars", "Jupiter", "Uranus", "Venus", "Venus"),
    QUESTION_3(" Which country produces the most coffee in the world?", "Vietnam", "Brazil", "Columbia", "America", "Brazil"),
    QUESTION_4("Which country invented tea?", "Italia", "Japonia", "China", "Romania", "China"),
    QUESTION_5("When is Canada Day?", "July 4th", "July 1st", "June 1st", "January 1st", "July 1st") {
    };

    private final String question;
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final String answer4;
    private final String rightAnswer;

    Questions(String question, String answer1, String answer2, String answer3, String answer4, String rightAnswer) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswer = rightAnswer;
    }

    public Questions getNext() {
        return values()[(ordinal() + 1) % values().length];
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer1() {
        return this.answer1;
    }

    public String getAnswer2() {
        return this.answer2;
    }

    public String getAnswer3() {
        return this.answer3;
    }

    public String getAnswer4() {
        return this.answer4;
    }

    public String getRightAnswer() {
        return this.rightAnswer;
    }
}
