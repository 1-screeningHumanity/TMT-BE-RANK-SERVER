package TMT.Ranking.kafka.application;

import TMT.Ranking.batch.infrastructure.DailyRankingQueryDslmp;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletIQueryDslImp;
import TMT.Ranking.kafka.dto.NicknameChangeDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
@Slf4j
public class KafkaConsumerService {

    private final ObjectMapper  mapper = new ObjectMapper();
    private final DailyRankingQueryDslmp dailyRankingQueryDslmp;
    private final DailyWalletIQueryDslImp dailyWalletIQueryDslImp;


    //Json Parsing 처리
    private <T> T parseMessage(String kafkaMessage, TypeReference<T> typeReference) {
        try{
            return mapper.readValue(kafkaMessage, typeReference);
        }catch (JsonProcessingException e){
            log.info("JsonProcessingException : {}", e.getMessage());
            return null;
        }
    }

    @KafkaListener(topics ="member-subscribe-changenickname")
    public void changenickname(String kafkaMessage){

        log.info("kafkaMessage : {}", kafkaMessage);

        NicknameChangeDto nicknameChangeDto = parseMessage(kafkaMessage,
                new TypeReference<NicknameChangeDto>(){});

        dailyRankingQueryDslmp.updateNickname(nicknameChangeDto);
        dailyWalletIQueryDslImp.updateNickname(nicknameChangeDto);

    }

}
