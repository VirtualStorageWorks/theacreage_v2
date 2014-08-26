package theacreage.User;

import javax.persistence.*;

/**
 * Created by WPMWM on 8/26/2014.
 */
@Entity
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    private String authority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
