package org.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommunityServiceTest {
    @Mock
    CommunityService communityService;

    @Mock
    ProfileService profileService;

    @DisplayName("Подписка пользователей на сообщества")
    @Test
    void shouldAddSubscribersToCommunity() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));

        Profile user = profileService.get(1L).get();
        Profile profile = profileService.get(2L).get();

        Community community = createTestCommunity(1L);
        Set<Profile> subscribers = new HashSet<>(List.of(user, profile));

        when(communityService.getParticipants(community.getId())).thenReturn(subscribers);
        when(communityService.subscribe(community.getId(), user.getId())).thenReturn(true);
        when(communityService.subscribe(community.getId(), profile.getId())).thenReturn(true);

        assertTrue(communityService.subscribe(1L, 1L));
        assertTrue(communityService.subscribe(1L, 2L));
        assertEquals(communityService.getParticipants(1L), subscribers);
    }

    @DisplayName("Получение списка подписчиков сообщества")
    @Test
    void getParticipants() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));
        when(profileService.get(3L)).thenReturn(Optional.of(createTestProfile(3L)));

        Profile part1 = profileService.get(1L).get();
        Profile part2 = profileService.get(2L).get();
        Profile part3 = profileService.get(3L).get();

        Community community = createTestCommunity(1L);

        Set<Profile> expectedParticipants = new HashSet<>(List.of(part1, part2, part3));

        when(communityService.getParticipants(community.getId())).thenReturn(expectedParticipants);

        assertEquals(expectedParticipants, communityService.getParticipants(1L));
    }

    @DisplayName("Отписка пользователей от сообщества")
    @Test
    void unsubscribe() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));
        when(profileService.get(3L)).thenReturn(Optional.of(createTestProfile(3L)));

        Profile part1 = profileService.get(1L).get();
        Profile part2 = profileService.get(2L).get();
        Profile part3 = profileService.get(3L).get();

        Community community = createTestCommunity(1L);

        Set<Profile> expectedParticipants = new HashSet<>(List.of(part1, part2));

        when(communityService.getParticipants(community.getId())).thenReturn(expectedParticipants);
        when(communityService.unsubscribe(community.getId(), part3.getId())).thenReturn(true);

        assertTrue(communityService.unsubscribe(1L, 3L));
        assertEquals(expectedParticipants, communityService.getParticipants(1L));
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

    private Community createTestCommunity(Long id) {
        return Community.builder()
                .id(id)
                .name("Name community")
                .description("Test community")
                .participants(new HashSet<>())
                .build();
    }
}