package org.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    @Mock
    private ProfileService profileService;

    @DisplayName("Создание профиля с:")
    @Test
    void shouldReturnProfileIfWeCreatedIt() {
        when(profileService.create(1L)).thenReturn(createTestProfile(1L));
        when(profileService.create(2L)).thenReturn(createTestProfile(2L));

        Profile user = createTestProfile(1L);
        Profile profile = createTestProfile(2L);

        assertEquals(profileService.create(1L), user);
        assertEquals(profileService.create(2L), profile);
    }

    @DisplayName("Обновление профиля")
    @Test
    void shouldReturnUpdatedProfileIfWeUpdatedIt() {
        Profile updatedUser = createTestProfile(1L);
        updatedUser.setName("New Name");
        updatedUser.setEmail("email@yandex.ru");

        when(profileService.update(updatedUser)).thenReturn(updatedUser);

        assertEquals(profileService.update(updatedUser), updatedUser);
    }

    @DisplayName("Получение профиля по ID")
    @Test
    void shouldGetProfileById() {
        Profile user = createTestProfile(1L);
        Profile profile = createTestProfile(2L);

        when(profileService.get(user.getId())).thenReturn(Optional.of(user));
        when(profileService.get(profile.getId())).thenReturn(Optional.of(profile));

        assertEquals(profileService.get(1L).get(), user);
        assertEquals(profileService.get(2L).get(), profile);
    }

    @DisplayName("Удаление профиля :с")
    @Test
    void shouldDeleteProfile() {
        when(profileService.delete(1L)).thenReturn(createTestProfile(1L));
        when(profileService.delete(2L)).thenReturn(createTestProfile(2L));

        Profile user = createTestProfile(1L);
        Profile profile = createTestProfile(2L);

        assertEquals(profileService.delete(1L), user);
        assertEquals(profileService.delete(2L), profile);
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