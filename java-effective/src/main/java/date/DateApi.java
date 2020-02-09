package date;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateApi {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        Date date = new Date();

        System.out.println(date);
        System.out.println(instant.atZone(ZoneId.of("PST", ZoneId.SHORT_IDS)));

        // localdatetime to instant
        LocalDateTime localDate = LocalDateTime.now();
        Instant instant2 = localDate.atZone(ZoneId.systemDefault()).toInstant();
        Instant instant3 = localDate.atZone(ZoneId.of("UTC", ZoneId.SHORT_IDS)).toInstant();
        System.out.println(instant2);
        System.out.println(instant3);
        System.out.println(localDate);

        // instant to localdatetime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.of("UTC", ZoneId.SHORT_IDS));
        LocalDateTime localDateTime3 = LocalDateTime.ofInstant(instant, ZoneId.of("CTT", ZoneId.SHORT_IDS));
        System.out.println(localDateTime);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
    }
    
    public static void DurationPeriod() {
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
