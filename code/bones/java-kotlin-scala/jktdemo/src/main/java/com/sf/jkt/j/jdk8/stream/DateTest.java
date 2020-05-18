package com.sf.jkt.j.jdk8.stream;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateTest {
    @Test
    public void testDuration()throws Exception{
        Instant instant = Instant.now();
        TimeUnit.SECONDS.sleep(2);
        Instant instant1 = Instant.now();
        Duration duration = Duration.between(instant,instant1);
        System.out.println(duration.toDays());
       Duration d2 = duration.plusDays(3);
        System.out.println(duration.toDays());
        System.out.println(d2.toDays());
        Clock  clock= Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant2=clock.instant();
        Date lagacyDate= Date.from(instant);
        System.out.println(millis);
        System.out.println(lagacyDate);
        ZoneId zoneId = ZoneId.of("Europe/Berlin");
        ZoneId zoneId2=ZoneId.of("Brazil/East");
        System.out.println(zoneId.getRules());
        System.out.println(zoneId2.getRules());
        LocalTime now1 =LocalTime.now(zoneId);
        LocalTime now2= LocalTime.now(zoneId2);
        System.out.println(now1.isBefore(now2));
        System.out.println(ChronoUnit.HOURS.between(now1,now2));
        System.out.println(ChronoUnit.MINUTES.between(now1,now2));

        DateTimeFormatter gf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMAN);
        System.out.println(LocalDate.parse("24.12.2014",gf));

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();

        System.out.println(dayOfWeek);    // FRIDAY


        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

         dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

         instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13




    }
}
