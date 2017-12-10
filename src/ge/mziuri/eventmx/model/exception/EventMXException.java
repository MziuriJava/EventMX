package ge.mziuri.eventmx.model.exception;

public class EventMXException extends Exception {
    private String messageKey;

    private Exception ex;

    public EventMXException(String messageKey) {
        this.messageKey = messageKey;
    }

    public EventMXException(String messageKey, Exception ex) {
        super(ex);
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
