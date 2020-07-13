package study.basiccrud.module.mn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int grade;

    @OneToMany(mappedBy = "student")
    private List<Enrolment> enrolments = new ArrayList<>();

}
