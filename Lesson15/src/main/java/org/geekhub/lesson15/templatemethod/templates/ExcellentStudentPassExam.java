package org.geekhub.lesson15.templatemethod.templates;

import org.geekhub.lesson15.Exam;

public class ExcellentStudentPassExam extends PassExamTemplate {
    @Override
    protected void prepare(Exam exam) {
        System.out.println("Learn all material during one week before exam: " + exam.getName());
        System.out.println("Repeat all material on " + exam.getTime().toLocalDate().minusDays(1) + " evening.");
    }

    @Override
    protected void wakeup(Exam exam) {
        System.out.println("Wake up at " + exam.getTime().minusHours(2) + ".");
    }

    @Override
    protected void pass(Exam exam) {
        System.out.println("Pass exam for 100.");
    }

    @Override
    protected void getMark(Exam exam) {
        System.out.println("Get mark into gradebook.");
    }
}
