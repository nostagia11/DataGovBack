package com.dats.datsconnexion.Repositories;

import com.dats.datsconnexion.entities.Tables;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TableRepo extends CrudRepository<Tables,Long> {

    Iterable<Tables> findTablesByNatNameContaining(String naturalname);
    Iterable<Tables> findTablesByTechNameContaining(String techname);

}
