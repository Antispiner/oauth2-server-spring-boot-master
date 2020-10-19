package com.internalproject.api.utils;

import com.internalproject.api.enums.Language;
import com.internalproject.api.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class NotificationUtil {

    private final Map<MessageType, Map<Language,Message>> message;

    public NotificationUtil() {
        this.message = new HashMap<>();

        Map<Language, Message> temp = new HashMap<>();
        temp.put(Language.RU, new Message("Подтверждение аккаунта", ""));
        message.put(MessageType.VERIFY_EMAIL, temp);
    }

    public Map<Language,Message> getByType(MessageType type){
        return message.get(type);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Message{
        private String title;
        private String body;
    }
}
