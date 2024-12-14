package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.Repositories.ColumnsRepo;
import com.dats.datsconnexion.Repositories.SoftwareTechRepo;
import com.dats.datsconnexion.Repositories.TableRepo;
import com.dats.datsconnexion.Repositories.TechStockRepo;
import com.dats.datsconnexion.entities.Columns;
import com.dats.datsconnexion.entities.SoftwareSolution;
import com.dats.datsconnexion.entities.Tables;
import com.dats.datsconnexion.entities.TechStock;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class SqlServerConnection {

    private TableRepo tableRepo;
    private ColumnsRepo columnsRepo;
    private SoftwareTechRepo softwareTechRepo;
    private TechStockRepo techStockRepo;



    public SqlServerConnection(ColumnsRepo columnsRepo, TableRepo tableRepo, SoftwareTechRepo softwareTechRepo, TechStockRepo techStockRepo) {
        this.columnsRepo = columnsRepo;
        this.tableRepo = tableRepo;
        this.softwareTechRepo=softwareTechRepo;
        this.techStockRepo=techStockRepo;
    }

    public void loadMetadata(TechStock dbRequest) throws SQLException {
        String connectionUrl = String.format("jdbc:sqlserver://%s:%d;databaseName=%s",
                dbRequest.getHost(), dbRequest.getPort(), dbRequest.getDbName());
        String dbname = dbRequest.getDbName();

        try (Connection connection = DriverManager.getConnection(
                connectionUrl, dbRequest.getUsername(), dbRequest.getEPassword())) {

            SoftwareSolution solution= new SoftwareSolution();
            TechStock techStock=new TechStock();

            techStock.setUrl(connectionUrl);
            techStock.setType("SQL Server");
            techStock.setConnMethod("JDBC");
            techStock.setHost(dbRequest.getHost());
            techStock.setPort(dbRequest.getPort());
            techStock.setDbName(dbRequest.getDbName());
            techStock.setEPassword(dbRequest.getEPassword());
            techStock.setUsername(dbRequest.getUsername());


            solution.setTechStock(techStock);
            techStockRepo.save(techStock);
            softwareTechRepo.save(solution);



            DatabaseMetaData metaData = connection.getMetaData();
            // Retrieve tables and columns metadata
            try (ResultSet tables = metaData.getTables(dbname, "dbo", "%", new String[]{"TABLE","VIEW","SYSTEM TABLE"})) {
                List<Tables> tablesList= new ArrayList<>();

                while (tables.next()) {

                    String tableName = tables.getString("TABLE_NAME");   // Table name
                    String tableType = tables.getString("TABLE_TYPE");   // Table type (TABLE, VIEW, etc.)
                    Tables table = new Tables();
                    table.setTechName(tableName);
                    table.setType(tableType);
                    table.setFormat(".dbo");
                    List<Columns> columns = new ArrayList<>();


                    try (ResultSet columnsRs = metaData.getColumns(null, null, tableName, "%")) {
                        Columns column = new Columns();
                        while (columnsRs.next()) {
                            String columnName = columnsRs.getString("COLUMN_NAME");



                            column.setTable(table);
                            column.setTname(columnName);

                        }

                        columns.add(column);
                        columnsRepo.saveAll(columns);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }


                    tablesList.add(table);
                }



                tableRepo.saveAll(tablesList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch  (Exception e) {
            e.printStackTrace();
        }
    }





}
