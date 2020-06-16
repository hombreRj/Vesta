package gg.scenarios.vesta.managers.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Accessors(fluent = true)
public class ProfileOptions {

    @Getter @Setter private List<UUID> ignored;
    @Getter @Setter private boolean receivingNewConversations = true;
    @Getter @Setter private boolean playingMessageSounds = true;


}
