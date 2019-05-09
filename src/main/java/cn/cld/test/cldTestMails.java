package cn.cld.test;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class cldTestMails {
    public static void main(String[] args) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人的邮箱地址
        message.setFrom("335252661@qq.com");
        //收件人的邮箱地址
        message.setTo("chengliude@tes-sys.com");
        //邮件主题
        message.setSubject("spring email test!!!!");
        //邮件内容
        message.setText("收到的邮件内容：spring email test ！！！");
        //发送邮件
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.send(message);

    }
}
