package com.checkins.autosuggestion.utils;

import com.checkins.autosuggestion.model.Location;
import com.checkins.autosuggestion.repo.AutosuggestionRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

@Component
public class DataReader {

    @Autowired
    private AutosuggestionRepository autosuggestionRepository;

    @Autowired
    public DataReader(@Value("${XLSX_FILE_PATH}") String dataResource) throws IOException {
        List<Location> locations = readData(dataResource);
        saveToRedis(locations);
    }
    private void saveToRedis(List<Location> locations){
        //autosuggestionRepository.deleteAll();
        locations.forEach(l -> autosuggestionRepository.save(l));
    }
    private List<Location> readData(String dataResource) throws IOException {
        File excelFile = new File(dataResource);
        InputStream inputStream = new FileInputStream(excelFile);
        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
        List<Location> locations = new LinkedList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue; // Skip the header row
            }
            String id = String.valueOf(row.getCell(0).getNumericCellValue());
            String name = row.getCell(1).getStringCellValue();
            String fullName = row.getCell(2).getStringCellValue();
            String type = row.getCell(3).getStringCellValue();
            String state = row.getCell(4).getStringCellValue();
            String country = row.getCell(5).getStringCellValue();
            String hierarchyPath = row.getCell(6).getStringCellValue();

            double latitude = row.getCell(7).getNumericCellValue();
            double longitude = row.getCell(8).getNumericCellValue();

            Location location = new Location(id, name, fullName, type, state, country, hierarchyPath, latitude, longitude);
            locations.add(location);
        }
        return locations;
    }
}
