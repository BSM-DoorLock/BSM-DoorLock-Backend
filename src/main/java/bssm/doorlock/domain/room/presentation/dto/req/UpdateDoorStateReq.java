package bssm.doorlock.domain.room.presentation.dto.req;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateDoorStateReq {

    @NotNull
    private Boolean state;
}
