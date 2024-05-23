import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "T_Roles")
public class Role implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role() {
        this.idRole = 0;
        this.roleName = "None";
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "[ " + this.roleName + " , " + this.idRole + " ]";
    }
}
