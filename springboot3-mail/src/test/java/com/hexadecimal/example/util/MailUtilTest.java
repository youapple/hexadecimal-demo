package com.hexadecimal.example.util;

import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MailUtilTest {
    @Resource
    private MailUtil mailUtil;

    /**
     * 测试简单邮件发送
     */
    @Test
    public void testSendSampleMail() {
        mailUtil.sendSampleMail("676981627@qq.com", "测试发送简单邮件", "hello world.");
        System.out.println("******执行发送简单邮件成功******");
    }

    /**
     * 测试带附件邮件发送
     */
    @Test
    public void testSendAttachmentMail() throws Exception {
        mailUtil.sendAttachmentMail("676981627@qq.com", "测试发送附件邮件", "hello world.", "preview.jpg", "/Users/youjianping/preview-test.jpg");
        System.out.println("******执行发送附件邮件成功******");
    }

    /**
     * 测试模板邮件发送
     */
    @Test
    public void testSendTemplateMail() throws Exception {
        mailUtil.sendTemplateMail("676981627@qq.com", "测试发送模板邮件",  "templates/mail_template.ftl", "展示效果1", "展示效果2");
        System.out.println("******执行发送模板邮件成功******");
    }

}
