package cc.bitbank.exception;

/**
 * Created by tanaka on 2017/04/11.
 */
public class BitbankException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -668176882481871028L;

    public static final int DEFAULT_CODE = 0;

    public final int code;

    public BitbankException() {
        this(DEFAULT_CODE);
    }

    public BitbankException(int code) {
        this(code, String.valueOf(code));
    }

    public BitbankException(String message) {
        this(DEFAULT_CODE, message);
    }

    public BitbankException(int code, String message) {

        super(message);

        this.code = code;

    }

}
