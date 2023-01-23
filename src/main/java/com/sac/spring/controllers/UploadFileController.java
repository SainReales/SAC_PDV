package com.sac.spring.controllers;

import java.io.IOException;
import java.util.List;

import com.sac.spring.models.dao.IClientDao;
import com.sac.spring.models.entity.Client;
import com.sac.spring.models.service.ClientServiceImpl;
import com.sac.spring.models.service.UploadFileImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;





@Controller
public class UploadFileController {
    @Autowired
    UploadFileImpl uploadfileimplements;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    IClientDao clientRepository;

    @RequestMapping("/UploadFileClient")
    public String saveFileExcelClient(MultipartFile file,Model model)throws IOException{
        this.uploadfileimplements.guardarFileClient(file);
        List<Client> listarClient = clientRepository.findAll();
        model.addAttribute("listarClient", listarClient);

        return "redirect:/clients";
    }

}
