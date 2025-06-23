package me.danhthanhf.distant_class.common.event;

import lombok.Getter;
import me.danhthanhf.distant_class.entity.User;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class VerifyEmailEvent extends ApplicationEvent {
    private User user;

    public VerifyEmailEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public VerifyEmailEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
