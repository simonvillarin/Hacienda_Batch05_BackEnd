package org.ssglobal.training.codes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssglobal.training.codes.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
