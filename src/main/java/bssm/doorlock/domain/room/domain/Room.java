package bssm.doorlock.domain.room.domain;

import bssm.doorlock.domain.room.presentation.dto.res.RoomRankingRes;
import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {

    @Id
    @Column(columnDefinition = "INT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean isOpen;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private final Set<RoomOwner> owners = new HashSet<>();

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private final Set<RoomGuest> guests = new HashSet<>();

    @Builder
    public Room(Long id, Boolean isOpen) {
        this.id = id;
        this.isOpen = isOpen;
    }

    public RoomRes toResponse() {
        return RoomRes.builder()
                .id(id)
                .isOpen(isOpen)
                .owners(owners.stream()
                        .map(RoomOwner::toResponse)
                        .toList())
                .guests(guests.stream()
                        .map(RoomGuest::toResponse)
                        .toList())
                .build();
    }

    public RoomRes toShareResponse() {
        return RoomRes.builder()
                .id(id)
                .isOpen(isOpen)
                .owners(owners.stream()
                        .map(RoomOwner::toResponse)
                        .toList())
                .build();
    }

    public RoomRankingRes toRankingResponse() {
        return RoomRankingRes.builder()
                .id(id)
                .totalGuests(guests.size())
                .owners(owners.stream()
                        .map(RoomOwner::toResponse)
                        .toList())
                .build();
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }
}
