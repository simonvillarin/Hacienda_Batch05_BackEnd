package org.ssglobal.training.codes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssglobal.training.codes.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
