package cx.exception;


import cx.enums.RResultEnum;
import lombok.Getter;

@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(RResultEnum rResultEnum) {
        super(rResultEnum.getMessage());
        this.code = rResultEnum.getCode();
    }

    public SellException(Integer code, String message ) {
        super(message);
        this.code = code;
    }
}
