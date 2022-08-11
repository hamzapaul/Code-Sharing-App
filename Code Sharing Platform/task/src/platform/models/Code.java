package platform.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Code {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Lob
    private String code;
    private String date;
    private long originalTime; //entered by user in seconds
    private long time; //remaining time
    private long views;

    private LocalDateTime timestamp; //time when uploaded

    private boolean isTimeRestricted;
    private boolean isViewsRestricted;
    public Code() {}
    public Code(String code, long time, long views) {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.timestamp = localDateTime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.date = localDateTime.format(formatter);
        this.code = code;
        this.originalTime = time;
        this.time = time;
        this.views = views;

        isTimeRestricted = time > 0;
        isViewsRestricted = views > 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public long getOriginalTime() {
        return originalTime;
    }

    public void setOriginalTime(long originalTime) {
        this.originalTime = originalTime;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @JsonIgnore
    public boolean isTimeRestricted() {
        return isTimeRestricted;
    }

    public void setTimeRestricted(boolean timeRestricted) {
        isTimeRestricted = timeRestricted;
    }

    @JsonIgnore
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @JsonIgnore
    public boolean isViewsRestricted() {
        return isViewsRestricted;
    }

    public void setViewsRestricted(boolean viewsRestricted) {
        isViewsRestricted = viewsRestricted;
    }
}
