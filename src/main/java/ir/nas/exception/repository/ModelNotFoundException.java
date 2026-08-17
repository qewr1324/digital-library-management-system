package ir.nas.exception.repository;

/**
 * ModelNotFoundException
 */
public class ModelNotFoundException extends RuntimeException
{
    public ModelNotFoundException(final String message)
    {
        super(message);
    }
}
