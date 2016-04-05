package com.kevin.learning.javamail;

import java.io.File;

/**
 * http://www.cnblogs.com/yejg1212/archive/2013/06/01/3112702.html
 * @author Administrator
 *
 */


public class Main {
	
	public static void main(String[] args){     
        //这个类主要是设置邮件     
     MailSenderInfo mailInfo = new MailSenderInfo();      
     mailInfo.setMailServerHost("smtp.126.com");      
     mailInfo.setMailServerPort("25");      
     mailInfo.setValidate(true);      
     mailInfo.setUserName("googlevsbing@126.com");      
     mailInfo.setPassword("...");//您的邮箱密码      
     mailInfo.setFromAddress("googlevsbing@126.com");      
     mailInfo.setToAddress("844063569@qq.com");      
     mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");      
     mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");      
        //这个类主要来发送邮件     
     SimpleMailSender sms = new SimpleMailSender();     
//     sms.sendTextMail(mailInfo);//发送文体格式      
     
     
     sms.sendHtmlMail(mailInfo,new File("D:/tmp/需求说明文档.docx"));//发送html格式     
   }  
}
