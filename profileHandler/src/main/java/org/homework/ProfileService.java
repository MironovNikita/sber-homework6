package org.homework;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Profile create(Long id);

    Profile update(Profile profile);

    Optional<Profile> get(Long profileId);

    Profile delete(Long profileId);
}
