package org.homework;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Community {
    private Long id;

    private String name;

    private String description;

    private Set<Profile> participants;
}
