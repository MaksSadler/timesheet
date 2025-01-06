package com.example.timesheet.repository;

import com.example.timesheet.model.User;
import com.example.timesheet.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

   // @Query(nativeQuery = true, value = "select role_name from users_roles ur where ur.user_id = :userId")
    List<UserRole> findByUserId(Long userId);
}
