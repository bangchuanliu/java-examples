package time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TimeExample {
    
    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.of(2019, 7, 15,3,15,23);
        LocalDateTime end = LocalDateTime.of(2019, 7, 16,3,15,23);

        // difference seconds between two LocalDateTime
        Duration duration = Duration.between(start, end);
        
        // difference seconds between two LocalDate
        Period period = Period.between(start.toLocalDate(), end.toLocalDate());
        
        // ChronoUnit.{unit}.between to find out the difference between dates based on unit
        long hourDiff = ChronoUnit.HOURS.between(start, end);
        long minDiff = ChronoUnit.MINUTES.between(start, end);
        
        System.out.println(duration.toHours());
        System.out.println(period.getDays());
        System.out.println(hourDiff);
        System.out.println(minDiff);
    }
}
