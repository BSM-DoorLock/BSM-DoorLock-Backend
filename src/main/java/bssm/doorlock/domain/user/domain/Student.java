package bssm.doorlock.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @Column(length = 10)
    private String studentId;

    @Column(columnDefinition = "year")
    private int enrolledAt;

    @Column(columnDefinition = "INT(1) UNSIGNED")
    private int grade;

    @Column(columnDefinition = "INT(1) UNSIGNED")
    private int classNo;

    @Column(columnDefinition = "INT(2) UNSIGNED")
    private int studentNo;

    @Column(length = 32)
    private String email;

    @OneToOne(mappedBy = "student")
    @org.springframework.data.annotation.Transient
    private User user;

    @Builder
    public Student(String studentId, int enrolledAt, int grade, int classNo, int studentNo, String email, User user) {
        this.studentId = studentId;
        this.enrolledAt = enrolledAt;
        this.grade = grade;
        this.classNo = classNo;
        this.studentNo = studentNo;
        this.email = email;
        this.user = user;
    }

    public void setEnrolledAt(int enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

}
