package bssm.doorlock.domain.user.domain.repository;

import bssm.doorlock.domain.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByEnrolledAtAndGradeAndClassNoAndStudentNo(int enrolledAt, int grade, int classNo, int studentNo);

}
