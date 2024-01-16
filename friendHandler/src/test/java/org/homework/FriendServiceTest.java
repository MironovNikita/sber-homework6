package org.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FriendServiceTest {
    @Mock
    private ProfileService profileService;

    @Mock
    private FriendService friendService;

    @DisplayName("Добавление друга с:")
    @Test
    void shouldBeTrueIfWeAddNewFriend() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));

        Profile user = profileService.get(1L).get();
        Profile friend = profileService.get(2L).get();

        List<Profile> expectedFriends = List.of(friend);

        when(friendService.getFriends(user.getId())).thenReturn(expectedFriends);
        when(friendService.addFriend(user.getId(), friend.getId())).thenReturn(true);

        assertTrue(friendService.addFriend(1L, 2L));
        assertEquals(expectedFriends, friendService.getFriends(1L));
    }

    @DisplayName("Получение списка друзей пользователя")
    @Test
    void shouldReturnFriendList() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));
        when(profileService.get(3L)).thenReturn(Optional.of(createTestProfile(3L)));

        Profile user = profileService.get(1L).get();
        Profile friend1 = profileService.get(2L).get();
        Profile friend2 = profileService.get(3L).get();

        List<Profile> expectedFriends = List.of(friend1, friend2);

        when(friendService.getFriends(user.getId())).thenReturn(expectedFriends);

        assertEquals(expectedFriends, friendService.getFriends(1L));
    }

    @DisplayName("Удаление друга :с")
    @Test
    void shouldBeTrueIfWeRemoveFriend() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));
        when(profileService.get(3L)).thenReturn(Optional.of(createTestProfile(3L)));

        Profile user = profileService.get(1L).get();
        Profile friend1 = profileService.get(2L).get();
        Profile friend2 = profileService.get(3L).get();

        List<Profile> expectedFriends = List.of(friend1);

        when(friendService.getFriends(user.getId())).thenReturn(expectedFriends);
        when(friendService.deleteFriend(user.getId(), friend2.getId())).thenReturn(true);

        assertTrue(friendService.deleteFriend(1L, 3L));
        assertEquals(expectedFriends, friendService.getFriends(1L));
    }

    private Profile createTestProfile(Long id) {
        return Profile.builder()
                .id(id)
                .email("test@yandex.ru")
                .name("Test User")
                .birthday(LocalDate.of(1995, 8, 14))
                .friendList(new HashSet<>())
                .build();
    }
}