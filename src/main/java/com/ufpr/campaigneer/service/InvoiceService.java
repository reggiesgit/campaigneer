package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Participation;
import org.jvnet.hk2.annotations.Service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 25/10/2021
 */

@Service
public interface InvoiceService {

    void init();
    String save(MultipartFile file, String fileName);
    void deleteAll();
    Resource load(String filename);
    Stream<Path> loadAll();

    String triggerAndSave(Participation part, MultipartFile invoice);
    String resolveAndSave(Participation part, MultipartFile invoice);

    void replaceFile(Participation flagged, MultipartFile invoice, String uuid) throws IOException;
}
