package com.dats.datsconnexion.Repositories;


import com.dats.datsconnexion.entities.Label;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepo extends CrudRepository<Label,Long> {


}
