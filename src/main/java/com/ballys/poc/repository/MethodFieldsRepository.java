package com.ballys.poc.repository;

import com.ballys.poc.model.MethodFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodFieldsRepository extends JpaRepository<MethodFields, Long> {

}
