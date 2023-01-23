package com.sac.spring.models.service;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sac.spring.models.entity.Client;

@Service
public class UploadFileImpl {
    @Autowired
    ClientServiceImpl clientService;

    List<Client> listClient = new ArrayList<>();

    public void guardarFileClient(MultipartFile file) throws IOException {
        System.out.print("FileC" + file.getOriginalFilename());
        InputStream externalfile = file.getInputStream();
        XSSFWorkbook libroClient = new XSSFWorkbook(externalfile);
        XSSFSheet hojaClient = libroClient.getSheetAt(0);
        Iterator<Row> rowsClient = hojaClient.iterator();
        Iterator<Cell> cellClient;
        Row row;
        Cell cell;
        rowsClient.next();
        while (rowsClient.hasNext()) {
            Client client = new Client();
            row = rowsClient.next();
            cellClient = row.cellIterator();
            while (cellClient.hasNext()) {
                cell = cellClient.next();
                int index = cell.getColumnIndex();
                switch (index) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        client.setName(cell.toString());
                    }
                    case 2:{
                        client.setlastname(cell.toString());
                    }
                    case 3:{
                        client.setEmail(cell.toString());
                    }
                    case 4:{
                        client.setIdentification(cell.toString());
                    }
                    case 5:{
                        client.setPhone(cell.toString());
                    }
                    case 6:{
                        client.setDirection(cell.toString());
                    }

                    default:
                        break;
                }
            }
            this.listClient.add(client);
        }
        libroClient.close();
        this.clientService.saveAll(listClient);

    }
}

