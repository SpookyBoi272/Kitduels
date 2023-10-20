package prod.spooky.kitduels.utils;

import java.util.*;

public class DuelRequest {


    private UUID sender;
    private UUID receiver;
    private long requestTime;

    public DuelRequest(UUID sender, UUID receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.requestTime = System.currentTimeMillis();
    }

    public UUID getSender() {
        return sender;
    }

    public UUID getReceiver() {
        return receiver;
    }

    public long getRequestTime() {
        return requestTime;
    }
}
