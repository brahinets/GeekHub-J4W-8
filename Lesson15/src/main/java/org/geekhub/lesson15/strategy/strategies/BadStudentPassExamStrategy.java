package org.geekhub.lesson15.strategy.strategies;

import org.geekhub.lesson15.Exam;

public class BadStudentPassExamStrategy implements PassExamStrategy {
    @Override
    public void passExam(Exam exam) {
        System.out.println("Play Dota 2 during all the time.");
        System.out.println("Wake up at " + exam.getTime().minusMinutes(10) + ".");
        System.out.println("Write off answers for all questions from excellent student notebook.");
        System.out.println("Pass exam for 90.");
        System.out.println("Get mark into gradebook.");
    }
}
