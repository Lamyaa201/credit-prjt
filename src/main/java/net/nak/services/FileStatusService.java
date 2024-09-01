package net.nak.services;

import net.nak.DTO.FileStatusResponse;

public interface FileStatusService {
    void incrementSuccessfulFiles();
    void incrementFailedFiles();
    FileStatusResponse getFileStatus();
}
