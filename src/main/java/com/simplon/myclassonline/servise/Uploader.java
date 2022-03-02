package com.simplon.myclassonline.servise;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Uploader {
    /**
     * Cette méthode va prendre un fichier, lui assigner un nom unique puis le stocker
     * dans le dossier resources/static/uploads
     * @param file Le fichier à sauvegarder
     * @return le nom du fichier qui sera stocké en bdd
     * @throws IOException
     */
    public String upload(MultipartFile file) throws IOException {
        //On crée un nom de fichier unique avec un uuid en prenant l'extension originale du fichier (.jpg, .png etc.)
        String fileName = "/uploads/" + UUID.randomUUID() + "."
                + StringUtils.getFilenameExtension(file.getOriginalFilename());

        //On crée le chemin vers le fichier et surtout le dossier où il sera stocké
        //Ici on le met dans le dossier static dont le contenu sera exposé au web par Spring
        Path path = Paths.get("src/main/resources/static" + fileName);
        //On met le fichier dans le dossier
        Files.copy(file.getInputStream(), path);

        return fileName;

    }
}
