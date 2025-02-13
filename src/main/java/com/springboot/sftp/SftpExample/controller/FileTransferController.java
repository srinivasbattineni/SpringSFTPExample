package com.springboot.sftp.SftpExample.controller;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.sftp.SftpExample.service.HornetQService;
import com.springboot.sftp.SftpExample.service.SftpService;

@RestController
public class FileTransferController {

    @Autowired
    private SftpService sftpService;

    @Autowired
    private HornetQService hornetQService;

    @GetMapping("/transfer-file")
    public String transferFile(@RequestParam String fileName, @RequestParam String queueName) {
        try {
            File file = sftpService.downloadFile(fileName);
            hornetQService.sendFileToQueue(file, queueName);
            return "File transferred successfully!";
        } catch (Exception e) {
            return "Error transferring file: " + e.getMessage();
        }
    }
}