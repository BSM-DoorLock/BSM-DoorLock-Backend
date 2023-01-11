package bssm.doorlock.domain.user.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentInfoRes {

    private String name;
    private int enrolledAt;
    private int grade;
    private int classNo;
    private int studentNo;

}
