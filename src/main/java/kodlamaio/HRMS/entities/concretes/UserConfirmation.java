package kodlamaio.HRMS.entities.concretes;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="user_confirmations")

public class UserConfirmation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="is_confirmed")
	private boolean isConfirmed;
	
	@Column(name="is_confirmed_date")
	private LocalDateTime isConfirmedDate;
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name="user_confirmation_type_id")
	private UserConfirmationType userConfirmationType;
	
	
	public UserConfirmation( User user,UserConfirmationType userConfirmationType, boolean isConfirmed) {
		this.setUser(user);
		this.setUserConfirmationType(userConfirmationType);
		this.setConfirmed(isConfirmed);
	}
}


