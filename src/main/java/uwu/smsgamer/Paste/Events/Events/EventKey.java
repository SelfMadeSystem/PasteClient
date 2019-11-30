package uwu.smsgamer.Paste.Events.Events;

import uwu.smsgamer.Paste.Events.Event;

public class EventKey extends Event {
    private int key;

    public EventKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}