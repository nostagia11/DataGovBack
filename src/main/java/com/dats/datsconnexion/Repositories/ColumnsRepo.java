package com.dats.datsconnexion.Repositories;


import com.dats.datsconnexion.entities.Columns;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnsRepo extends CrudRepository<Columns,Long> {

    Iterable<Columns> findColumnsByTableTechName(String tableTechName);
    Iterable<Columns> findColumnsByTableNatName(String tableNatName);


    Iterable<Columns>  findColumnsBySynoContaining(String synonym);

    Iterable<Columns>  findColumnsByLabelName(String label);


}
