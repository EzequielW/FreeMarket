package com.example.freemarket.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileStorageUtil{
	@Value("${spring.datasource.images-path}")
	private String imagePath;
	
    public String writeToFile(byte[] bytes, String fileName) throws IOException {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        String relativePath = "/products/" + randomUUIDString + fileExtension;
        String filePath = imagePath + relativePath;

        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(bytes);
        fos.flush();
        fos.close();

        return relativePath;
    }
}
