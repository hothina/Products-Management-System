package exception;

import java.io.IOException;

public class CSVFileException extends RuntimeException {
    public CSVFileException(IOException exception) {
        super(exception);
    }
}
