package com.tara.org.repository;

import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the UserExtra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserExtraRepository extends JpaRepository<UserExtra,Long> {
    
	@Query("select u from UserExtra u where u.user = :user")
	UserExtra findByUser(@Param("user") User user);
}
