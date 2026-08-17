package ir.nas.exception.db;

/**
 * DBConnectionException
 */
public class DBConnectionException extends RuntimeException
{
    public DBConnectionException(final String message)
    {
        super(message);
    }
}
