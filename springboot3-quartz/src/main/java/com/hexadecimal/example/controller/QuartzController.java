package com.hexadecimal.example.controller;

import com.hexadecimal.example.quartz.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/quartz")
public class QuartzController {

    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 新增定时任务
     *
     * @param jobName      任务名称，示例：helloJob
     * @param jobGroup     任务组，示例：helloJobGroup
     * @param triggerName  触发器名称，示例：helloTrigger
     * @param triggerGroup 触发器组，示例：helloTriggerGroup
     * @param jobClass     调度任务，示例：HelloJob
     * @param cron         cron表达式，示例：0 0/1 * * * ?
     * @return String
     */
    @PostMapping(path = "/addJob")
    @ResponseBody
    public String addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String jobClass, String cron) {
        try {
            quartzJobService.addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass, cron);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 暂停任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     * @return String
     */
    @PostMapping(path = "/pauseJob")
    @ResponseBody
    public String pauseJob(String jobName, String jobGroup) {
        try {
            quartzJobService.pauseJob(jobName, jobGroup);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 恢复任务
     *
     * @param jobName  任务名称
     * @param jobGroup 任务组
     * @return String
     */
    @PostMapping(path = "/resumeJob")
    @ResponseBody
    public String resumeJob(String jobName, String jobGroup) {
        try {
            quartzJobService.resumeJob(jobName, jobGroup);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 重启任务
     *
     * @param triggerName  触发器名称，示例：helloTrigger
     * @param triggerGroup 触发器组，示例：helloTriggerGroup
     * @param cron     cron表达式
     * @return String
     */
    @PostMapping(path = "/rescheduleJob")
    @ResponseBody
    public String rescheduleJob(String triggerName, String triggerGroup, String cron) {
        try {
            quartzJobService.rescheduleJob(triggerName, triggerGroup, cron);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 删除任务
     *
     * @param jobName      任务名称，示例：helloJob
     * @param jobGroup     任务组，示例：helloJobGroup
     * @param triggerName  触发器名称，示例：helloTrigger
     * @param triggerGroup 触发器组，示例：helloTriggerGroup
     * @return String
     */
    @PostMapping(path = "/deleteJob")
    @ResponseBody
    public String deleteJob(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        try {
            quartzJobService.deleteJob(jobName, jobGroup, triggerName, triggerGroup);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 查询所有定时任务
     * @return List<String>
     */
    @PostMapping(path = "/listJobs")
    @ResponseBody
    public List<String> listJobs() {
        try {
            return quartzJobService.listJobs();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

}

