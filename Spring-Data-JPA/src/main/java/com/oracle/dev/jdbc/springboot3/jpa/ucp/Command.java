package com.oracle.dev.jdbc.springboot3.jpa.ucp;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_COMMANDS", schema = "SYSTEM")
public class Command implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDCOMMAND")
    private int idCommand;
    
    @ManyToOne @JoinColumn(name="IDUSER", nullable=false)
    private User user;
    @Column(name = "COMMANDDATE")
    private Date commandDate;
    
    
    public Command() {}
    
    public Command(int id, User user, Date commandDate ) {
    	this.setId(id);
        this.setUser( user );
        this.setCommandDate( commandDate );
    }

    public void setId(int id) {
    	this.idCommand=id;
		
	}

	public int getIdCommand() {
        return idCommand;
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
