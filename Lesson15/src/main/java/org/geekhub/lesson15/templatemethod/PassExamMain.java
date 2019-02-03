package org.geekhub.lesson15.templatemethod;

import org.geekhub.lesson15.Exam;
import org.geekhub.lesson15.templatemethod.templates.BadStudentPassExam;
import org.geekhub.lesson15.templatemethod.templates.ExcellentStudentPassExam;
import org.geekhub.lesson15.templatemethod.templates.RegularStudentPassExam;

import java.time.LocalDateTime;
import java.time.Month;

public class PassExamMain {
    public static void main(String[] args) {
        final Exam exam = new Exam();
        exam.setName("Programing Languages");
        exam.setTeacher("Teacher Name");
        exam.setTime(LocalDateTime.of(2018, Month.MAY, 25, 9, 0));

        passExam(new Student(new ExcellentStudentPassExam()), exam);
        passExam(new Student(new BadStudentPassExam()), exam);
        passExam(new Student(new RegularStudentPassExam()), exam);
    }

    private static void passExam(Student student, Exam exam) {
        student.getTemplate().passExam(exam);
    }
}
