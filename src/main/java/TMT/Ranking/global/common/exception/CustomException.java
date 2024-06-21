package TMT.Ranking.global.common.exception;


import TMT.Ranking.global.common.response.BaseResponseCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final BaseResponseCode status;

    public CustomException(BaseResponseCode status) {
        this.status = status;
    }
}