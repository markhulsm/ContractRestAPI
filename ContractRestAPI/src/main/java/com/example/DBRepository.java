package com.example;
import java.util.*;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface DBRepository extends Repository<Contract, Long>
{
	List<Contract> findByStatus(@Param("status") Contract.Status status);
	
	Optional<Contract> findById(@Param("id") Long id);
	
	void delete(@Param("id") Long id);
	
	Contract save(Contract contract);
}
