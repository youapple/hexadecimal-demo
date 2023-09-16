package com.hexadecimal.example.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestJob {
    @XxlJob("testJob")
    public ReturnT<String> testJob(String date) {
        log.info("---------testJob定时任务执行成功--------");
        XxlJobHelper.log("---------testJob定时任务执行成功--------");
        return ReturnT.SUCCESS;
    }
}
