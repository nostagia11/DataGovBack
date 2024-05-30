package com.dats.datsconnexion.Repositories;


import com.dats.datsconnexion.entities.TechStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechStockRepo extends CrudRepository<TechStock,Long> {
}
