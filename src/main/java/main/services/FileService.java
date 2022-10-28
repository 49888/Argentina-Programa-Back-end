package main.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FileService {
    

    private String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/argentina-programa-abb9b.appspot.com/o/images%2Fangular.png?alt=media";

    public String getExtension(String fileName) {

        return fileName.substring(fileName.lastIndexOf("."));
    }


    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        
        File tempFile = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {

            fos.write(multipartFile.getBytes());

            fos.close();
        }
        
        return tempFile;
    }

    public String uploadFile(File file, String name) throws IOException {

        String projectId = "argentina-programa-abb9b";

        String bucketName = "argentina-programa-abb9b.appspot.com";

        String type = "image/" + getExtension(name).replace(".", "");

        BlobId blobId = BlobId.of(bucketName, name);

        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(type).build();
        
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./serviceAccountKey.json"));

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        Blob blob = storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String name_encode = URLEncoder.encode(name, "utf-8").toString();

        return "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" + name_encode + "?alt=media";
    }
}
