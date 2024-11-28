package com.dats.datsconnexion.Repositories;

import com.dats.datsconnexion.entities.Documentation;
import com.dats.datsconnexion.entities.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationRepository extends JpaRepository<Documentation,Long> {


}
