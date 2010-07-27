package main.control.command.exceptions;

/**
 * @author Esteban I. Invernizzi
 * @created 12/04/2010
 */
public abstract class NestedException extends Throwable {

    private Throwable cause;

    public NestedException() {}

    public NestedException(Throwable cause) {
        this.cause = cause;
    }

    public NestedException(String message) {
		super(message);
	}

	public Throwable getCause() {
        return cause;
    }
}
