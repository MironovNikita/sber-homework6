package org.homework;

import lombok.Builder;
import lombok.Data;

import java.util.Queue;

@Data
@Builder
public class Dialogue {
    private Long id;

    private Long userId;

    private Long companionId;

    private Queue<Message> messages;
}
