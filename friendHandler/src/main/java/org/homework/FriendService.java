package org.homework;

import java.util.List;
import java.util.Optional;

public interface FriendService {
    boolean addFriend(Long profileId, Long friendId);

    List<Profile> getFriends(Long profileId);

    boolean deleteFriend(Long profileId, Long friendId);
}
