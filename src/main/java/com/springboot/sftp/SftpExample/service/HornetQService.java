package com.springboot.sftp.SftpExample.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;

@Service
public class HornetQService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendFileToQueue(File file, String queueName) throws Exception {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        jmsTemplate.convertAndSend(queueName, fileContent);
    }
}