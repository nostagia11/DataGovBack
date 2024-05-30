package com.dats.datsconnexion.Repositories;


import com.dats.datsconnexion.entities.SoftwareSolution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareTechRepo extends CrudRepository<SoftwareSolution,Long> {


    SoftwareSolution  findSoftwareSolutionByNameS(String name);
    Iterable<SoftwareSolution> findSoftwareSolutionByType(String type);

    }
