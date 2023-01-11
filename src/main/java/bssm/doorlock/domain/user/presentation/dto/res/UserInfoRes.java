package bssm.doorlock.domain.user.presentation.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoRes {

    private Long code;
    private String nickname;
    private String email;

}
