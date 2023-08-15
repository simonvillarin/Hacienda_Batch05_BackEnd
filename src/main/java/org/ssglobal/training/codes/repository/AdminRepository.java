package org.ssglobal.training.codes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssglobal.training.codes.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

}
