# Rank Server

## 📖 Description
- 스프링 배치를 이용해 일간, 주간, 월간 수익률 집계를 합니다.
- 스프링 배치를 이용해 자산 변동률을 집계 합니다.
- 스케쥴러를 이용해 매주 월~금, 매월말일에 랭킹 정산을 진행합니다.
- 스케쥴러를 이용해 매주 월~금 자산 랭킹을 정산합니다.

## ⚙ Function
1. 일간, 주간, 월간 수익률 랭킹을 확인할수 있습니다.
2. 자산 의 내림차순 순위를 볼수 있습니다 자산은 예수금만 적용되어있습니다.
3. 페인 클라언트를 이용해 매일 새로운 지갑 데이터를 들고옵니다.
   
## 🔧 Stack
 - **Language** : Java 17
 - **Library & Framework** : Spring Boot 3.3.0
 - **Database** : MYSQL, Redis
 - **ORM** : "JPA"
 - **Deploy** : AWS EC2 / Jenkins
 - **Dependencies** : JWT, Validation, Lombok, Model Mapper, Swagger, Eureka, Kakfa, FeignClient, springBatch, springWeb, QUeryDsl, 

## 🔧 Architecture
- **Design Patter** : Hexagonal Architecture
- **Micro Service Architecture** : Spring Cloud
- **Event-Driven Architecture** : Kafka

## 👨‍👩‍👧‍👦 Developer
*  **강성욱** ([KangBaekGwa](https://github.com/KangBaekGwa))
*  **김도형** ([ddohyeong](https://github.com/ddohyeong))
*  **박태훈** ([hoontaepark](https://github.com/hoontaepark))
