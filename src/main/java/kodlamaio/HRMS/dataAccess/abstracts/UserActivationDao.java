package kodlamaio.HRMS.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.HRMS.entities.concretes.UserActivation;

public interface UserActivationDao extends JpaRepository<UserActivation, Integer> {
	
	
	List<UserActivation> getByIsActivated(boolean isActivated);
	
	UserActivation getByCode (String code);
	
	UserActivation getByUser_Id (int userId);
	
	
	


}
