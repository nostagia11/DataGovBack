package com.dats.datsconnexion.Services;

import com.dats.datsconnexion.Repositories.ColumnsRepo;
import com.dats.datsconnexion.Repositories.SoftwareTechRepo;
import com.dats.datsconnexion.Repositories.TableRepo;
import com.dats.datsconnexion.Repositories.TechStockRepo;
import com.dats.datsconnexion.entities.Columns;
import com.dats.datsconnexion.entities.SoftwareSolution;
import com.dats.datsconnexion.entities.Tables;
import com.dats.datsconnexion.entities.TechStock;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;


@Service
public class ExcelRead {

    private  TableRepo tableRepo;
    private  ColumnsRepo columnsRepo;
    private SoftwareTechRepo softwareTechRepo;
    private TechStockRepo techStockRepo;


    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    public ExcelRead(ColumnsRepo columnsRepo, TableRepo tableRepo, SoftwareTechRepo softwareTechRepo, TechStockRepo techStockRepo) {
        this.columnsRepo = columnsRepo;
        this.tableRepo = tableRepo;
        this.softwareTechRepo=softwareTechRepo;
        this.techStockRepo=techStockRepo;
    }



    public void scanExcel(MultipartFile file) throws IOException {


        Workbook workbook = null;
        List<Columns> columnsList = new ArrayList<>();
        List<Tables> tablesList = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            workbook = WorkbookFactory.create(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SoftwareSolution solution= new SoftwareSolution();
        TechStock techStock=new TechStock();
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
        Path path=get(DIRECTORY, filename).toAbsolutePath();
        System.out.println(path);
        techStock.setUrl(String.valueOf(fileStorage));

        solution.setNameS(filename);

        solution.setTechStock(techStock);
        techStockRepo.save(techStock);
        softwareTechRepo.save(solution);

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet datatypeSheet = workbook.getSheetAt(i);
            Tables table = new Tables();
            table.setTechName(workbook.getSheetName(i));
            table.setFormat(".xlsx");
            table.setNatName(workbook.getSheetName(i));
            tablesList.add(table);

            Row currentRow = datatypeSheet.getRow(0);
             Iterator<Cell> cellIterator = currentRow.iterator();

             while (cellIterator.hasNext()) {

             Cell currentCell = cellIterator.next();
               Columns column = new Columns();
                 column.setTable(table);
                column.setTname(currentCell.getStringCellValue());


                 Row currentRow1 = datatypeSheet.getRow(1);
                 Iterator<Cell> cellIterator1 = currentRow1.iterator();


                 while (cellIterator1.hasNext()) {
                     Cell currentCell1 = cellIterator1.next();


                     switch (currentCell1.getCellType()) {
                         case STRING:
                             column.setType("string");

                             break;
                         case NUMERIC:

                             column.setType("int");
                             break;
                         case FORMULA:
                             column.setType("int");

                             break;
                         default:
                             column.setType("null");
                     }


                 }
               columnsList.add(column);
            }

            tableRepo.saveAll(tablesList);
            columnsRepo.saveAll(columnsList);


        }

        workbook.close();

    }








}

