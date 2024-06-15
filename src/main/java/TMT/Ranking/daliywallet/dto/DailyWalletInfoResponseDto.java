package TMT.Ranking.daliywallet.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DailyWalletInfoResponseDto {

    private List<DataDto> data;
    @Getter
    public static class DataDto {

        private String uuid;
        private Long won;

        @Builder
        public DataDto(String uuid, Long won) {
            this.uuid = uuid;
            this.won = won;
        }
    }

}