package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.ParticipationDAO;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.ParticipationService;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 21/08/2021
 */

@Component
public class ParticipationComponent implements ParticipationService {

    ParticipationDAO dao = new ParticipationDAO();
    private static final String INVOICE_FOLDER = "/invoices/";

    @Override
    public Optional<Participation> create(Participation participation) {
        return Optional.ofNullable(dao.create(participation));
    }

    @Override
    public Optional<Participation> update(Participation participation) {
        return Optional.ofNullable(dao.update(participation));
    }

    @Override
    public void delete(Participation participation) {
        dao.delete(participation);
    }

    @Override
    public Optional<Participation> findById(Long id) {
        return Optional.ofNullable(dao.findById(id));
    }

    @Override
    public Optional<Participation> uploadInvoice(Long id, MultipartFile invoice) throws IOException {
        Participation part = dao.findById(id);

        byte[] imgBytes = invoice.getBytes();
        Path path = Paths.get(INVOICE_FOLDER + "invoice_" + part.getId());
        Files.write(path, imgBytes);

//        part.setInvoice64(encode(invoice));
        return Optional.ofNullable(dao.update(part));
    }

//    private String encode(MultipartFile invoice) throws IOException {
//        byte[] fileContent = invoice.getBytes();
//        return Base64.getEncoder().encodeToString(fileContent);
//    }
//
//    private MultipartFile decode(String invoice64) throws IOException {
//        byte[] data = Base64.getDecoder().decode(invoice64);
//        try (OutputStream stream = new FileOutputStream("img/invoice.png")) {
//            stream.write(data);
//
//        }
//        return new CommonsMultipartFile(tempFile);
//    }
}
