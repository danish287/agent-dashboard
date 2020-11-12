package com.infosys.agentdashboard.repository;

import com.infosys.agentdashboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepo")
public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findByName(String name);
}
