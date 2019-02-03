package org.geekhub.lesson15.strategy.strategies;

import org.geekhub.lesson15.Exam;

public class ExcellentStudentPassExamStrategy implements PassExamStrategy {
    @Override
    public void passExam(Exam exam) {
        System.out.println("Learn all material during one week before exam: " + exam.getName());
        System.out.println("Repeat all material on " + exam.getTime().toLocalDate().minusDays(1) + " evening.");
        System.out.println("Wake up at " + exam.getTime().minusHours(2) + ".");
        System.out.println("Pass exam for 100.");
        System.out.println("Get mark into gradebook.");
    }
}
