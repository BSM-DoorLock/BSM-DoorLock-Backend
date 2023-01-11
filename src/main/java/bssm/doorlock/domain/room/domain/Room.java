package bssm.doorlock.domain.room.domain;

import bssm.doorlock.domain.room.presentation.dto.res.RoomRes;
import bssm.doorlock.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private final List<User> owners = new ArrayList<>();

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
                        .map(User::toUserResponse)
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
                        .map(User::toUserResponse)
                        .toList())
                .build();
    }

}
