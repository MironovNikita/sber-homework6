package org.homework;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Message {
    private Long id;

    private Long userId;

    private String message;

    private LocalDateTime sent;
}
