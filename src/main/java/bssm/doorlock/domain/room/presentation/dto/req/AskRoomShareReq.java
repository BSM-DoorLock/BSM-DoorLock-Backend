package bssm.doorlock.domain.room.presentation.dto.req;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AskRoomShareReq {

    @NotBlank
    private String ownerStudentId;
}
