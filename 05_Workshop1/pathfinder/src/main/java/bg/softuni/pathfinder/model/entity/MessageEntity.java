package bg.softuni.pathfinder.model.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name="messages")
public class MessageEntity extends BaseEntity{

    @Column(name="date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name="text_content", columnDefinition = "text")
    private String textContent;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private UserEntity recipient;


    public MessageEntity(){}

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public MessageEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public MessageEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public MessageEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getRecipient() {
        return recipient;
    }

    public MessageEntity setRecipient(UserEntity recipient) {
        this.recipient = recipient;
        return this;
    }
}
