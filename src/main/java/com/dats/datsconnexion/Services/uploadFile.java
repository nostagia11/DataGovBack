package com.dats.datsconnexion.Services;


import com.dats.datsconnexion.Repositories.SoftwareTechRepo;
import com.dats.datsconnexion.Repositories.TechStockRepo;
import com.dats.datsconnexion.entities.SoftwareSolution;
import com.dats.datsconnexion.entities.TechStock;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class uploadFile {

    private SoftwareTechRepo softwareTechRepo;
    private TechStockRepo techStockRepo;


    public uploadFile (SoftwareTechRepo softwareTechRepo,TechStockRepo techStockRepo){
        this.softwareTechRepo=softwareTechRepo;
        this.techStockRepo=techStockRepo;
    }
    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    // Define a method to upload files

    public ResponseEntity<List<String>> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<String> filenames = new ArrayList<>();

        for(MultipartFile file : multipartFiles) {
            SoftwareSolution solution= new SoftwareSolution();
            TechStock techStock=new TechStock();
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            techStock.setUrl(String.valueOf(fileStorage));
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            solution.setNameS(filename);

            solution.setTechStock(techStock);
            filenames.add(filename);
            softwareTechRepo.save(solution);
            techStockRepo.save(techStock);

        }
        return ResponseEntity.ok().body(filenames);
    }
}
