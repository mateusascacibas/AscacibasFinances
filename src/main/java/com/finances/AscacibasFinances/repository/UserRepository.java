package com.finances.AscacibasFinances.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finances.AscacibasFinances.enumerator.TypeEnum;
import com.finances.AscacibasFinances.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByType(TypeEnum type);

}
