package net.idrok.tester.controller;

import net.idrok.tester.entity.Fayl;
import net.idrok.tester.service.FaylService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/fayl")
public class FaylController {

    private Logger logger = LoggerFactory.getLogger(FaylController.class.getName());


    private String ROOT_DIRECTORY = "files";

    private final FaylService faylService;

    @Value("${system.root-directory}")
    private void setDirectory(String url) {
        this.ROOT_DIRECTORY = url;

    }


    public FaylController(FaylService faylService) {
        this.faylService = faylService;
    }


    @GetMapping()
    public ResponseEntity<List<Fayl>> getAll(@RequestParam(name = "key", required = false) String key,
                                             HttpServletRequest req, HttpServletResponse res) {
        if (key == null) key = "";
        return ResponseEntity.ok(faylService.getAll(key));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fayl> getById(@PathVariable Long id) {
        return ResponseEntity.ok(faylService.getById(id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        Fayl f = faylService.getById(id);

        File file = new File(ROOT_DIRECTORY + "/" + f.getId() + "_" + f.getNom());
        if (file.exists()) {

            try {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");

                MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
                if(f.getTur() != null){
                    mediaType = MediaType.parseMediaType(f.getTur());
                }

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(mediaType)
                        .body(resource);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }


        return ResponseEntity.notFound().build();
    }


    @PostMapping("/upload")
    public ResponseEntity<Fayl> upload(@RequestParam("file") MultipartFile file) {
        Fayl f = new Fayl();
        f.setNom(file.getOriginalFilename());
        f.setTur(file.getContentType());
        f = faylService.create(f);
        try {
            File saqlashFayl = new File(ROOT_DIRECTORY);
            if (!saqlashFayl.exists()) saqlashFayl.mkdirs();

            saqlashFayl = new File(ROOT_DIRECTORY + "/" + f.getId() + "_" + f.getNom());

            saqlashFayl.createNewFile();

            FileOutputStream fos = new FileOutputStream(saqlashFayl);
            fos.write(file.getBytes());
            fos.close();
            return ResponseEntity.ok(f);

        } catch (IOException e) {
           logger.error(e.getMessage());
        }
        faylService.delete(f);

        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        faylService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
