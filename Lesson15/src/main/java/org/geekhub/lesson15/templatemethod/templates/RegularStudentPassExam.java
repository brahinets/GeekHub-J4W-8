package org.geekhub.lesson15.templatemethod.templates;

import org.geekhub.lesson15.Exam;

public class RegularStudentPassExam extends PassExamTemplate {
    @Override
    protected void prepare(Exam exam) {
        System.out.println("Prepare cribs on " + exam.getTime().toLocalDate().minusDays(1) + " evening and night.");
    }

    @Override
    protected void wakeup(Exam exam) {
        System.out.println("Wake up at " + exam.getTime().minusMinutes(30) + ".");
    }

    @Override
    protected void pass(Exam exam) {
        System.out.println("Pass exam for 75.");
    }

    @Override
    protected void getMark(Exam exam) {
        System.out.println("Get mark into gradebook.");
    }
}
