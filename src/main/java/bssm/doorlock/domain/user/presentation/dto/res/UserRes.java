package bssm.doorlock.domain.user.presentation.dto.res;

import bssm.doorlock.domain.user.domain.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRes {

    private Long code;
    private String name;
    private UserRole role;

}
