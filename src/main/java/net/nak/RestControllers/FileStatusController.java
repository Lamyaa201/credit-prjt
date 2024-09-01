package net.nak.RestControllers;

import net.nak.DTO.FileStatusResponse;
import net.nak.services.FileStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FileStatusController {

    private final FileStatusService fileStatusService;

    @Autowired
    public FileStatusController(FileStatusService fileStatusService) {
        this.fileStatusService = fileStatusService;
    }

    @GetMapping("/file-status")
    public ResponseEntity<FileStatusResponse> getFileStatus() {
        FileStatusResponse response = fileStatusService.getFileStatus();
        return ResponseEntity.ok(response);
    }
}
