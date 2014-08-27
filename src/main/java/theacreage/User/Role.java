package theacreage.User;

import javax.persistence.*;

/**
 * Created by wpmwm on 8/27/2014.
 */
@Entity
@Table(name="roles")
public class Role {

    public Role(){}

    public Role(User user, String role){
        this.setRole(role);
    }

    @Id
    @GeneratedValue
    private int id;

    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
