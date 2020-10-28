package gg.scenarios.vesta.enums;

import lombok.Getter;

@Getter
public enum ServerType {

    UHC("Ultra Hard Core"),
    FFA("Free For All"),
    HUB("Lobby"),
    TOURNAMENT("Tournament"),
    DEV("Development");

    private final String name;

    ServerType(String name) {
        this.name = name;
    }
}
