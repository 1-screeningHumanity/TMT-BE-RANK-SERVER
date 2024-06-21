package TMT.Ranking.weeklyranking.application;

import org.springframework.scheduling.annotation.Scheduled;

public interface WeeklyRankingService {

    void createWeeklyRanking();

    @Scheduled(cron = "0 30 17 ? * FRI")
    void updateChangeWeeklyRanking();

    @Scheduled(cron = "0 40 17 ? * FRI")
    void updateLastWeekRanking();
}
