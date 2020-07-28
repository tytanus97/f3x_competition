package f3x.competition.F3XCompetition.exceptions;

public class FileStorageException extends RuntimeException {

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message,Throwable cause) {
        super(message,cause);
    }

}
