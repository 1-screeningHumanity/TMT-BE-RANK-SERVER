package TMT.Ranking.daliywallet.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class DailyWalletInfoResponseDto {

    private List<DataDto> data;
    @Getter
    public static class DataDto {

        private String uuid;
        private Long won;
        private String nickname;

    }

}