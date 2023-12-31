package prod.spooky.kitduels.utils;

import java.util.*;

public class DuelRequest {

    private final UUID sender;
    private final UUID receiver;
    //private long requestTime;
    private final String kit;
    private final String map;

    public DuelRequest(UUID sender, UUID receiver, String kit, String map) {
        this.sender = sender;
        this.receiver = receiver;
        //this.requestTime = System.currentTimeMillis();
        this.kit= kit;
        this.map= map;
    }

    public String getKit() {
        return kit;
    }

    public String getMap() {
        return map;
    }

    public UUID getSender() {
        return sender;
    }

    public UUID getReceiver() {
        return receiver;
    }

}
