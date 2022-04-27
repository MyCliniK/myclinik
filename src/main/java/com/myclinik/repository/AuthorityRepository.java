package com.myclinik.repository;

import com.myclinik.model.Authority;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer>{

}
