package org.geekhub.lesson15.strategy;

import org.geekhub.lesson15.Exam;
import org.geekhub.lesson15.strategy.strategies.BadStudentPassExamStrategy;
import org.geekhub.lesson15.strategy.strategies.ExcellentStudentPassExamStrategy;
import org.geekhub.lesson15.strategy.strategies.RegularStudentPassExamStrategy;

import java.time.LocalDateTime;
import java.time.Month;

public class PassExamMain {
    public static void main(String[] args) {
        final Exam exam = new Exam();
        exam.setName("Programing Languages");
        exam.setTeacher("FirstName LastName");
        exam.setTime(LocalDateTime.of(2018, Month.MAY, 25, 9, 0));

        passExam(new Student(new ExcellentStudentPassExamStrategy()), exam);
        passExam(new Student(new BadStudentPassExamStrategy()), exam);
        passExam(new Student(new RegularStudentPassExamStrategy()), exam);
    }

    private static void passExam(Student student, Exam exam) {
        student.getStrategy().passExam(exam);
    }
}
