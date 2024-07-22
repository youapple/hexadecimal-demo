package com.hexadecimal.example;

import com.hexadecimal.example.utils.MessageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class I18nTest {
    @Test
    public void testI18n() {
        LocaleContextHolder.setLocale(Locale.CHINA); // 测试中文环境
        //LocaleContextHolder.setLocale(Locale.US); // 测试英文环境
        System.out.println(MessageUtils.message("test.message.remind"));
    }

}
