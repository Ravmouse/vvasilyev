package ru.job4j.h5testtask.t2sqlruparser;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс, расширяющий Job (что делать), в котором также есть триггер (когда делать).
 */
public class SchedJob implements Job {
    /**
     * Ссылка на файл настроек.
     */
    private static Properties prop;

    /**
     * @param args .
     * @throws SchedulerException .
     */
    public static void main(String[] args) throws SchedulerException {
        final String arg = args[0];
        prop = Utils.createAndLoadProp(arg); //Создается Properties и в него загружается файл настроек, чтобы потом
        //передать эту ссылку триггеру и конструктору DataBaseConnection.
        final Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        final JobDetail job = JobBuilder.newJob(SchedJob.class).build();
        final Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(prop.getProperty("cron.time")))
                .build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

    /**
     * @param jec jec.
     */
     @Override
     public void execute(JobExecutionContext jec) {
         try (final DataBaseConnection dbc = new DataBaseConnection(prop)) {
             HTMLPageStore html = new HTMLPageStore(dbc.jobMap);
             try {
                 if (dbc.isInfoState()) {
                     dbc.requestAllData();
                     html.connectAndGetOffer("D");
                 } else {
                     html.connectAndGetOffer("Y");
                 }
             } catch (IOException io) {
                 io.printStackTrace();
             }
             dbc.sendDataToDB();
         } catch (SQLException sql) {
             sql.printStackTrace();
         }
     }
}