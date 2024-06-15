//package TMT.Ranking.kafka.application;
//
//
//import TMT.Ranking.kafka.dto.StockinfoDto;
//import TMT.Ranking.stockinfo.application.StockInfoServiceImp;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Getter
//@Service
//@Slf4j
//public class KafkaConsumerService {
//
//    private final ObjectMapper  mapper = new ObjectMapper();
//    private final StockInfoServiceImp stockInfoService;
//
//
//    //Json Parsing 처리
//    private <T> T parseMessage(String kafkaMessage, TypeReference<T> typeReference) {
//        try{
//            return mapper.readValue(kafkaMessage, typeReference);
//        }catch (JsonProcessingException e){
//            log.info("JsonProcessingException : {}", e.getMessage());
//            return null;
//        }
//    }
//
//    @KafkaListener(topics = "chart-rank-sotckinfo")
//    public void stockinfo(String kafkaMessage){
//        log.info("kafkaMessage : {}", kafkaMessage);
//
//        StockinfoDto stockinfoDto = parseMessage(kafkaMessage,
//                new TypeReference<StockinfoDto>(){});
//
//        if(stockinfoDto != null) {
//            log.info("stockinfoDto : {}", stockinfoDto);
//        }
//        stockInfoService.stockInfoSave(stockinfoDto);
//    }
//
////    @KafkaListener(topics = "trade-rank-")
//}
