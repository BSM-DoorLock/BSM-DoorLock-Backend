package bssm.doorlock.domain.user.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentRes {

    private String name;
    private String studentId;
    private Boolean isUser;

}
