FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/Ranking-0.0.1.jar RankingServer.jar
ENTRYPOINT ["java", "-jar", "RankingServer.jar"]