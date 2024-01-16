package org.homework;

import java.util.List;

public interface DialogueService {
    boolean createDialogue(Long profileId, Long companionId);

    List<Dialogue> getDialogues(Long profileId);

    boolean deleteDialogue(Long profileId, Long friendId);
}
