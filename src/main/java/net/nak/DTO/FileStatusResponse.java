package net.nak.DTO;

public class FileStatusResponse {

    private int successfulFiles;
    private int failedFiles;

    public FileStatusResponse(int successfulFiles, int failedFiles) {
        this.successfulFiles = successfulFiles;
        this.failedFiles = failedFiles;
    }

    public int getSuccessfulFiles() {
        return successfulFiles;
    }

    public void setSuccessfulFiles(int successfulFiles) {
        this.successfulFiles = successfulFiles;
    }

    public int getFailedFiles() {
        return failedFiles;
    }

    public void setFailedFiles(int failedFiles) {
        this.failedFiles = failedFiles;
    }
}
