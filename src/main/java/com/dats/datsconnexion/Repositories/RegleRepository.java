package com.dats.datsconnexion.Repositories;

import com.dats.datsconnexion.entities.Regle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegleRepository extends JpaRepository<Regle, Long> {}
