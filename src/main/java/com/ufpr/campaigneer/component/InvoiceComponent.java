package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.enums.CampaignStatus;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 25/10/2021
 */

@Component
public class InvoiceComponent implements InvoiceService {

    private static final String UNDERSCORE = "_";
    private final Path root = Paths.get("src/main/resources/invoices");
    Logger logger = LoggerFactory.getLogger(InvoiceComponent.class);

    ParticipationComponent participationComponent = new ParticipationComponent();

    @Override
    public void init() {
        try {
            File directory = new File(String.valueOf(root));
            if (!directory.exists()){
                Files.createDirectory(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file, String fileName) {
        try {
            init();
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
            logger.debug("Saved invoice with name: " + fileName);
            return String.valueOf(this.root.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public String triggerAndSave(Participation part, MultipartFile invoice) {
        String prefix = "";
        Participation current = participationComponent.resolveCampaing(part);
        if (CampaignStatus.NO_CAMPAIGN.equals(current.getCampaignStatus())) {
            prefix = CampaignStatus.NO_CAMPAIGN.name();
        } else {
            prefix = current.getTriggeredCampaign().getCode();
        }
        return resolveAndSave(prefix, part.getId(), invoice);
    }

    @Override
    public String resolveAndSave(Participation part, MultipartFile invoice) {
        return resolveAndSave(part.getTriggeredCampaign().getCode(), part.getId(), invoice);
    }

    public String resolveAndSave(String prefix, Long id, MultipartFile invoice) {
        String fileName = prefix + UNDERSCORE + id + ".jpg";
        return save(invoice, fileName);
    }
}
