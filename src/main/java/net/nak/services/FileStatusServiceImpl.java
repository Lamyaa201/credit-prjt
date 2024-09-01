package net.nak.services;

import net.nak.DTO.FileStatusResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FileStatusServiceImpl implements FileStatusService {

    private final AtomicInteger successfulFilesCount = new AtomicInteger();
    private final AtomicInteger failedFilesCount = new AtomicInteger();

    @Override
    public void incrementSuccessfulFiles() {
        successfulFilesCount.incrementAndGet();
    }

    @Override
    public void incrementFailedFiles() {
        failedFilesCount.incrementAndGet();
    }

    @Override
    public FileStatusResponse getFileStatus() {
        return new FileStatusResponse(successfulFilesCount.get(), failedFilesCount.get());
    }
}
