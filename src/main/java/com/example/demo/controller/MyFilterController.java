package com.example.demo.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MyFilterController {
    //跳转上传地址
    @RequestMapping("/fileUpload")
    public String fileUpload(ModelMap map){
        return "TestTomcatFile";
    }
    private static final String UPLOADED_FOLDER = "src\\main\\resources\\file\\";
    //上传功能
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:fileUpload";
        }
        try {
            byte[] bytes = file.getBytes();
            // UPLOADED_FOLDER ⽂件本地存储地址
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/fileUpload";
    }

    @RequestMapping("/exception")
    public String getException(){
        int i = 1/0;
        return "redirect:/fileUpload";
    }
}
