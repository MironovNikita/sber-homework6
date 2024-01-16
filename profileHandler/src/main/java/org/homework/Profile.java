package org.homework;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class Profile {
        private Long id;

        private String email;

        private String name;

        private LocalDate birthday;

        private Set<Profile> friendList;
}
