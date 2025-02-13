package com.springboot.sftp.SftpExample.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Service
public class SftpService {

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.port}")
    private int port;

    @Value("${sftp.user}")
    private String user;

    @Value("${sftp.password}")
    private String password;

    @Value("${sftp.remote.directory}")
    private String remoteDirectory;

    @Value("${sftp.local.directory}")
    private String localDirectory;

    public File downloadFile(String fileName) throws Exception {
        DefaultSftpSessionFactory sessionFactory = new DefaultSftpSessionFactory();
        sessionFactory.setHost(host);
        sessionFactory.setPort(port);
        sessionFactory.setUser(user);
        sessionFactory.setPassword(password);
        sessionFactory.setAllowUnknownKeys(true);

        SftpSession session = sessionFactory.getSession();
        InputStream inputStream = session.readRaw(remoteDirectory + "/" + fileName);

        File localFile = new File(localDirectory + "/" + fileName);
        org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, localFile);

        session.close();
        return localFile;
    }
}