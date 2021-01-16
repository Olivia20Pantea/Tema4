package com.company;

import javax.swing.*;

public class Main {
    private static final int TOTAL_QUESTIONS_NUMBER = 5;
    private static int questionNumber = 1;
    private static int rightAnswers = 0;

    private static JRadioButton jRadioButton1;
    private static JRadioButton jRadioButton2;
    private static JRadioButton jRadioButton3;
    private static JRadioButton jRadioButton4;
    private static JButton submitButton;
    private static JButton nextQuestionButton;
    private static JButton finishButton;

    public static void main(String[] args) {
        Questions question = Questions.QUESTION_1;
        JPanel panel = buildPanel(question);
        addActionToSubmitButton(panel, question);
    }

    public static void addActionToSubmitButton(JPanel panel, Questions question) {
        submitButton.addActionListener(
                e -> {
                    panel.updateUI();
                    String answer = null;
                    if (jRadioButton1.isSelected())
                        answer = question.getAnswer1();
                    else if (jRadioButton2.isSelected())
                        answer = question.getAnswer2();
                    else if (jRadioButton3.isSelected())
                        answer = question.getAnswer3();
                    else if (jRadioButton4.isSelected())
                        answer = question.getAnswer4();
                    if (answer == null) {
                        mustSelectOneOptionError(panel, question);
                    } else {
                        panel.removeAll();
                        if (answerIsRight(answer, question))
                            rightAnswer(panel, question);
                        else
                            wrongAnswer(panel, question);
                    }
                }
        );
    }

    public static void addActionToNextButton(JPanel panel, Questions question) {
        nextQuestionButton.addActionListener(
                e -> {
                    panel.updateUI();
                    if (questionNumber < TOTAL_QUESTIONS_NUMBER)
                        askNextQuestion(panel, question);
                    else
                        addFinishButton(panel);
                }
        );
    }

    public static void addActionToFinishButton(JPanel panel) {
        finishButton.addActionListener(
                e -> {
                    panel.updateUI();
                    showResults(panel);
                }
        );
    }

    private static void askNextQuestion(JPanel panel, Questions question) {
        panel.removeAll();
        questionNumber++;
        placeComponents(panel, question.getNext());
        addActionToSubmitButton(panel, question.getNext());
    }

    private static void mustSelectOneOptionError(JPanel panel, Questions question) {
        placeComponents(panel, question);

        JLabel mustSelectLabel = new JLabel("* You have to select one option. *");
        mustSelectLabel.setBounds(20, 190, 500, 25);
        panel.add(mustSelectLabel);

        addActionToSubmitButton(panel, question);
    }

    private static void addNextQuestionButton(JPanel panel, Questions question) {
        nextQuestionButton = new JButton("Next >>>");
        nextQuestionButton.setBounds(50, 200, 150, 25);
        panel.add(nextQuestionButton);
        addActionToNextButton(panel, question);
    }

    private static void addFinishButton(JPanel panel) {
        finishButton = new JButton("Finish !");
        finishButton.setBounds(50, 200, 150, 25);
        panel.add(finishButton);
        addActionToFinishButton(panel);
    }

    private static void addTheButtonThatComesNext(JPanel panel, Questions question) {
        if (questionNumber == TOTAL_QUESTIONS_NUMBER)
            addFinishButton(panel);
        else
            addNextQuestionButton(panel, question);
    }

    private static void wrongAnswer(JPanel panel, Questions question) {
        JLabel label;
        label = new JLabel("Your answer is wrong. Try the next one.");
        label.setBounds(50, 120, 500, 25);
        panel.add(label);

        label = new JLabel("The right answer was: " + question.getRightAnswer());
        label.setBounds(50, 150, 500, 25);
        panel.add(label);

        addTheButtonThatComesNext(panel, question);
    }

    private static void rightAnswer(JPanel panel, Questions question) {
        rightAnswers++;

        JLabel questionNumberLabel = new JLabel("Your answer is right. Congrats!");
        questionNumberLabel.setBounds(50, 120, 500, 25);
        panel.add(questionNumberLabel);

        addTheButtonThatComesNext(panel, question);
    }

    private static boolean answerIsRight(String answer, Questions question) {
        return answer.equals(question.getRightAnswer());
    }

    private static void showResults(JPanel panel) {
        panel.removeAll();
        JLabel label;
        label = new JLabel("STATUS:");
        label.setBounds(50, 60, 500, 25);
        panel.add(label);
        label = new JLabel("Total questions: " + TOTAL_QUESTIONS_NUMBER);
        label.setBounds(100, 90, 500, 25);
        panel.add(label);
        label = new JLabel("Right answers: " + rightAnswers);
        label.setBounds(100, 120, 500, 25);
        panel.add(label);
        label = new JLabel("Wrong answers: " + (TOTAL_QUESTIONS_NUMBER - rightAnswers));
        label.setBounds(100, 150, 500, 25);
        panel.add(label);
        label = new JLabel("Right answers accuracy: " + (float) (rightAnswers * 100) / TOTAL_QUESTIONS_NUMBER + "%");
        label.setBounds(100, 180, 500, 25);
        panel.add(label);
    }

    private static JPanel buildPanel(Questions question) {
        JFrame frame = new JFrame("Insert data: ");
        frame.setSize(600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel, question);

        frame.setVisible(true);

        return panel;
    }

    private static void placeComponents(JPanel panel, Questions question) {
        panel.removeAll();
        panel.setLayout(null);

        JLabel questionNumberLabel = new JLabel("Question: " + questionNumber + "/" + TOTAL_QUESTIONS_NUMBER);
        questionNumberLabel.setBounds(10, 20, 80, 25);
        panel.add(questionNumberLabel);

        JLabel questionLabel = new JLabel("Q: " + question.getQuestion());
        questionLabel.setBounds(20, 80, 400, 25);
        panel.add(questionLabel);

        jRadioButton1 = new JRadioButton();
        jRadioButton1.setText(question.getAnswer1());
        jRadioButton1.setBounds(90, 100, 200, 25);
        panel.add(jRadioButton1);

        jRadioButton2 = new JRadioButton();
        jRadioButton2.setText(question.getAnswer2());
        jRadioButton2.setBounds(90, 120, 200, 25);
        panel.add(jRadioButton2);

        jRadioButton3 = new JRadioButton();
        jRadioButton3.setText(question.getAnswer3());
        jRadioButton3.setBounds(90, 140, 200, 25);
        panel.add(jRadioButton3);

        jRadioButton4 = new JRadioButton();
        jRadioButton4.setText(question.getAnswer4());
        jRadioButton4.setBounds(90, 160, 200, 25);
        panel.add(jRadioButton4);

        ButtonGroup answersButton = new ButtonGroup();
        answersButton.add(jRadioButton1);
        answersButton.add(jRadioButton2);
        answersButton.add(jRadioButton3);
        answersButton.add(jRadioButton4);

        submitButton = new JButton("Submit");
        submitButton.setBounds(30, 230, 150, 25);
        panel.add(submitButton);
    }

}