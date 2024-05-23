import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_COMMANDS", schema = "SYSTEM")
public class Command implements Serializable {
    @Id 
    private int idCommand;
    
    @ManyToOne 
    @JoinColumn(name="idUser", nullable=false)
    private User user;
    
    private Date commandDate;
    
    public Command() {}
    
    public Command(int idCommand, User user, Date commandDate ) {
        this.idCommand = idCommand;
        this.setUser( user );
        this.setCommandDate( commandDate );
    }

    public int getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Date getCommandDate() {
        return commandDate;
    }
    
    public void setCommandDate(Date commandDate) {
        this.commandDate = commandDate;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Commande de >> " ).append( this.user )
               .append( " - " ).append( this.commandDate ).append( "\n" );
        return builder.toString();
    }   

}
