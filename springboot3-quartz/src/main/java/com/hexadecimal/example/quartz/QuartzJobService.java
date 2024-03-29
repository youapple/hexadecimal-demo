package com.hexadecimal.example.quartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuartzJobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 新增定时任务
     *
     * @param jobName
     * @param jobGroup
     * @param triggerName
     * @param triggerGroup
     * @param jobClass
     * @param cron
     */
    public void addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String jobClass, String cron) throws Exception {
        Class<? extends Job> jobClazz = (Class<? extends Job>) Class.forName("com.hexadecimal.example.job." + jobClass);
        JobDetail jobDetail = JobBuilder.newJob(jobClazz)
                .withIdentity(jobName, jobGroup)
                .storeDurably()
                .build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 重启定时任务
     *
     * @param triggerName
     * @param triggerGroup
     * @param cron
     */
    public void rescheduleJob(String triggerName, String triggerGroup, String cron) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        scheduler.rescheduleJob(triggerKey, cronTrigger);
    }

    /**
     * 暂停定时任务
     *
     * @param jobName
     * @param jobGroup
     */
    public void pauseJob(String jobName, String jobGroup) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
    }

    /**
     * 恢复定时任务
     *
     * @param jobName
     * @param jobGroup
     */
    public void resumeJob(String jobName, String jobGroup) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
    }

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     * @param triggerName
     * @param triggerGroup
     * @throws Exception
     */
    public void deleteJob(String jobName, String jobGroup, String triggerName, String triggerGroup) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
        scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroup));
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
    }

    /**
     * 查询所有定时任务
     *
     * @return
     */
    public List<String> listJobs() throws Exception {
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
        return jobKeys.stream().map(JobKey::getName).collect(Collectors.toList());
    }

}

