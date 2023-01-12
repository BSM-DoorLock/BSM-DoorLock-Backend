package bssm.doorlock.domain.room.presentation.dto.res;

import bssm.doorlock.domain.user.presentation.dto.res.StudentRes;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
public class RoomRankingRes implements Comparable<RoomRankingRes> {

    private Long id;
    private List<StudentRes> owners;
    private int totalGuests;

    @Override
    public int compareTo(@NotNull RoomRankingRes ranking) {
        if (ranking.totalGuests < totalGuests) {
            return -1;
        } else if (ranking.totalGuests > totalGuests) {
            return 1;
        }
        return 0;
    }
}
