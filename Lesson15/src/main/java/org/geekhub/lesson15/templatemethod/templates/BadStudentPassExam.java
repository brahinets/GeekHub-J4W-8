package org.geekhub.lesson15.templatemethod.templates;

import org.geekhub.lesson15.Exam;

public class BadStudentPassExam extends PassExamTemplate {
    @Override
    protected void prepare(Exam exam) {
        System.out.println("Play Dota 2 during all the time.");
    }

    @Override
    protected void wakeup(Exam exam) {
        System.out.println("Wake up at " + exam.getTime().minusMinutes(10) + ".");
    }

    @Override
    protected void pass(Exam exam) {
        System.out.println("Write off answers for all questions from excellent student notebook.");
        System.out.println("Pass exam for 90.");
    }

    @Override
    protected void getMark(Exam exam) {
        System.out.println("Get mark into gradebook.");
    }
}
