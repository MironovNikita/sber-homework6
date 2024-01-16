package org.homework;

import java.util.Set;

public interface CommunityService {
    boolean subscribe(Long communityId, Long profileId);

    Set<Profile> getParticipants(Long communityId);

    boolean unsubscribe(Long communityId, Long profileId);
}
