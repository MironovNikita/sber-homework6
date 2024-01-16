package org.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DialogueServiceTest {
    @Mock
    private ProfileService profileService;

    @Mock
    private DialogueService dialogueService;

    @DisplayName("Создание диалога с другим пользователем")
    @Test
    void createDialogue() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));

        Profile user = profileService.get(1L).get();
        Profile companion = profileService.get(2L).get();

        Dialogue dialogue = createTestDialogue(1L, user.getId(), companion.getId());

        when(dialogueService.createDialogue(user.getId(), companion.getId())).thenReturn(true);

        assertTrue(dialogueService.createDialogue(1L, 2L));
        assertEquals(dialogue.getUserId(), user.getId());
        assertEquals(dialogue.getCompanionId(), companion.getId());
    }

    @DisplayName("Получение всех диалогов пользователя")
    @Test
    void getDialogues() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));
        when(profileService.get(3L)).thenReturn(Optional.of(createTestProfile(3L)));

        Profile user = profileService.get(1L).get();
        Profile friend1 = profileService.get(2L).get();
        Profile friend2 = profileService.get(3L).get();

        Dialogue dialogue1 = createTestDialogue(1L, user.getId(), friend1.getId());
        Dialogue dialogue2 = createTestDialogue(2L, user.getId(), friend2.getId());

        List<Dialogue> expectedDialogues = List.of(dialogue1, dialogue2);

        when(dialogueService.getDialogues(user.getId())).thenReturn(expectedDialogues);

        assertEquals(expectedDialogues, dialogueService.getDialogues(1L));
    }

    @DisplayName("Удаление диалога с другим пользователем")
    @Test
    void deleteDialogue() {
        when(profileService.get(1L)).thenReturn(Optional.of(createTestProfile(1L)));
        when(profileService.get(2L)).thenReturn(Optional.of(createTestProfile(2L)));
        when(profileService.get(3L)).thenReturn(Optional.of(createTestProfile(3L)));

        Profile user = profileService.get(1L).get();
        Profile friend1 = profileService.get(2L).get();
        Profile friend2 = profileService.get(3L).get();

        Dialogue dialogue1 = createTestDialogue(1L, user.getId(), friend1.getId());
        Dialogue dialogue2 = createTestDialogue(2L, user.getId(), friend2.getId());

        List<Dialogue> expectedDialogues = List.of(dialogue1);

        when(dialogueService.getDialogues(user.getId())).thenReturn(expectedDialogues);
        when(dialogueService.deleteDialogue(user.getId(), friend2.getId())).thenReturn(true);

        assertTrue(dialogueService.deleteDialogue(1L, 3L));
        assertEquals(expectedDialogues, dialogueService.getDialogues(1L));
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

    private Dialogue createTestDialogue(Long id, Long userId, Long companionId) {
        return Dialogue.builder()
                .id(id)
                .userId(userId)
                .companionId(companionId)
                .messages(new ArrayDeque<>())
                .build();
    }
}