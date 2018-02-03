package com.msrazavi.tamim.repository;

import com.msrazavi.tamim.model.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
}
