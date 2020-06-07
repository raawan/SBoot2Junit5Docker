package com.brexit.employee.repository;

import java.util.List;
import java.util.UUID;

import com.brexit.employee.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    List<Address> findByEmployeeId(UUID id);
}
