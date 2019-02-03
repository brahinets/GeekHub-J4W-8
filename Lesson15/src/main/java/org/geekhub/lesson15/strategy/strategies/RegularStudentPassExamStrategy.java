package org.geekhub.lesson15.strategy.strategies;

import org.geekhub.lesson15.Exam;

public class RegularStudentPassExamStrategy implements PassExamStrategy {
    @Override
    public void passExam(Exam exam) {
        System.out.println("Prepare cribs on " + exam.getTime().toLocalDate().minusDays(1) + " evening and night.");
        System.out.println("Wake up at " + exam.getTime().minusMinutes(30) + ".");
        System.out.println("Pass exam for 75.");
        System.out.println("Get mark into gradebook.");
    }
}
