package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {

    @Id
    private Long id;
    private String userFirstName;
    private String userLastName;
    private String username;
    private String lastCallBackQuery;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Note> notes;

    public User() {
    }

    public String getLastCallBackQuery() {
        return lastCallBackQuery;
    }

    public void setLastCallBackQuery(String lastCallBackQuery) {
        this.lastCallBackQuery = lastCallBackQuery;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
