package bssm.doorlock.domain.user.presentation.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRes {

    private Long code;
    private String name;
    private String studentId;

}
