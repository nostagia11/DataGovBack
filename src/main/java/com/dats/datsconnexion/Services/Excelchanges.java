package com.dats.datsconnexion.Services;
import com.dats.datsconnexion.Repositories.SoftwareTechRepo;
import com.dats.datsconnexion.entities.SoftwareSolution;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class Excelchanges {

    private SoftwareTechRepo softwareTechRepo;

    public Excelchanges (SoftwareTechRepo softwareTechRepo){
        this.softwareTechRepo=softwareTechRepo;
    }


        private Map<String, String> previousValues = new HashMap<>(); // Map to store previous cell values

        public Map<String, String> watchExcelFileChanges(Long soluId) throws IOException {
            SoftwareSolution solu=softwareTechRepo.findById(soluId).get();
            String filePath=solu.getTechStock().getUrl();
            System.out.println(filePath);
            MultipartFile resource = (MultipartFile) new ClassPathResource(
                    filePath).getFile();

            InputStream inputStream = resource.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                watchSheetChanges(sheet);
            }

            workbook.close();
            inputStream.close();
            return previousValues;
        }

        private void watchSheetChanges(Sheet sheet) {
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellAddress = cell.getAddress().formatAsString();
                    String currentValue = getCellValueAsString(cell);
                    String previousValue = previousValues.get(cellAddress);

                    if (previousValue == null || !previousValue.equals(currentValue)) {
                        // Cell value has changed
                        String changeInfo = "Date: " + new Date() +
                                ", Sheet: " + sheet.getSheetName() +
                                ", Cell Address: " + cell.getAddress() +
                                ", Previous Value: " + previousValue +
                                ", Current Value: " + currentValue;
                        System.out.println(changeInfo);

                        // Update the previous value
                        previousValues.put(cellAddress, currentValue);
                    }
                }
            }

        }

        private String getCellValueAsString(Cell cell) {
            if (cell == null) {
                return "";
            }

            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    // Evaluate formula cells
                    FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                    CellValue cellValue = evaluator.evaluate(cell);
                    return getCellValueAsString(cellValue);
                default:
                    return "";
            }
        }

        private String getCellValueAsString(CellValue cellValue) {
            switch (cellValue.getCellType()) {
                case STRING:
                    return cellValue.getStringValue();
                case NUMERIC:
                    return String.valueOf(cellValue.getNumberValue());
                case BOOLEAN:
                    return String.valueOf(cellValue.getBooleanValue());
                default:
                    return "";
            }
        }


}
