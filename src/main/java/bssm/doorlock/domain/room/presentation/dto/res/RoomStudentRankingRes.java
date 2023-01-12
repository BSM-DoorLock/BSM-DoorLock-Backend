package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.user.presentation.dto.res.StudentRes;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class RoomStudentRankingRes implements Comparable<RoomStudentRankingRes> {

    private StudentRes student;
    private int totalSharedRooms;

    @Override
    public int compareTo(@NotNull RoomStudentRankingRes ranking) {
        if (ranking.totalSharedRooms < totalSharedRooms) {
            return -1;
        } else if (ranking.totalSharedRooms > totalSharedRooms) {
            return 1;
        }
        return 0;
    }
}
