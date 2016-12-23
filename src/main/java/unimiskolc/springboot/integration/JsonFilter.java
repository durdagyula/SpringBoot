package unimiskolc.springboot.integration;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;

import java.io.File;

public class JsonFilter implements MessageSelector {
    public boolean accept(Message<?> message) {
        if (message.getPayload() instanceof File
                && !((File) message.getPayload()).getName().endsWith(".json")) {
            return false;
        }
        return true;
    }
}
