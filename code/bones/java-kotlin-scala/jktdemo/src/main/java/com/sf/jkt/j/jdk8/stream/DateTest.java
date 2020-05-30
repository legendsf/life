package com.sf.jkt.j.jdk8.stream;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/***
 *  好的 java8 date 实例
 *  https://www.jianshu.com/p/797716dc49fb
 */

public class DateTest {

    @Test
    public void testZoneId(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        ZoneId zoneId = ZoneId.of("GMT");
        ZonedDateTime zonedDateTime =ZonedDateTime.of(localDateTime,zoneId);
        System.out.println(zonedDateTime);
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId1 =ZoneId.systemDefault();
        System.out.println(zoneId);
        System.out.println(zoneId1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime local1Gmt = LocalDateTime.ofInstant(date.toInstant(),zoneId);
        LocalDateTime localDate=LocalDateTime.ofInstant(date.toInstant(),zoneId1);
        String gmtStr=local1Gmt.format(dateTimeFormatter);
        String localStr=localDate.format(dateTimeFormatter);
        String gmtZstr= localDate.atZone(ZoneId.of("GMT-8")).format(dateTimeFormatter);
        System.out.println(gmtZstr);
        System.out.println(gmtStr);
        System.out.println(localStr);
    }

    @Test
    public void testDate2LocaleDateTime(){
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME,"UTC"));
        dateFormat.setLenient(false);
        String str=dateFormat.format(date);
        System.out.println(str);


        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        LocalDateTime localDateTime1= LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
        LocalDateTime localDateTime2= date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Instant instant = localDateTime1.toInstant(ZoneOffset.UTC);
        System.out.println(instant);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);

        LocalDateTime utc_0= localDateTime1.minusHours(8);
        System.out.println(utc_0);

        SimpleDateFormat dateFormatGmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mgmtdate=dateFormatGmt.format(date);
        String mlocaldate=dateFormatLocal.format(date);
        System.out.println(mgmtdate);
        System.out.println(mlocaldate);
        System.out.println(TimeZone.getDefault());


        LocalDate localDate = localDateTime1.toLocalDate();

        LocalTime localTime = localDateTime1.toLocalTime();

        LocalDateTime localDateTime3 = localDate.atStartOfDay();

        LocalDateTime localDateTime4 = localTime.atDate(localDate);

        LocalDateTime localDateTime5 = LocalDateTime.of(localDate, localTime);


    }



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
