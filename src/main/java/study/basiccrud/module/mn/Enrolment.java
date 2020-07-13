package study.basiccrud.module.mn;

import javax.persistence.*;

@Entity
public class Enrolment {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

}
