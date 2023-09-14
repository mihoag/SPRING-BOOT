package com.springboot.email;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class maincontroller {

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/")
	public String index()
	{
		return "contactform";
	}
	
	@RequestMapping(value = "/contact" , method = RequestMethod.POST )
	public String contact(HttpServletRequest request, @RequestParam("attachment") MultipartFile fi) throws MessagingException, UnsupportedEncodingException { 
		
		String from = "leminhhoang123456le@gmail.com";
		String to = "tknhatgpt10@gmail.com";
		
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		String mailsubject = fullname + "has sent an email";
		String mailcontent = "<p><b>Sender name : </b>" + fullname + "</p>";
		mailcontent += "<p><b>Sender email : </b>" + email + "</p>";
		mailcontent += "<p><b>Sender subject : </b>" + subject + "</p>";
		mailcontent += "<p><b>Sender content : </b>" + content + "</p>";
		mailcontent += "<hr><img src = 'cid:logoImage'/>";
		
		
		helper.setFrom(from, "MinhHoang");
		helper.setTo(to);
		helper.setSubject(mailsubject);
		helper.setText(mailcontent, true);
		
		ClassPathResource resource = new ClassPathResource("/static/logo.png");
		helper.addInline("logoImage", resource);
		
	   
		if(!fi.isEmpty())
		{
			String filename = StringUtils.cleanPath(fi.getOriginalFilename());
			InputStreamSource source  = new InputStreamSource() {
				
				@Override
				public InputStream getInputStream() throws IOException {
					// TODO Auto-generated method stub
					return fi.getInputStream();
				}
			};
			
			helper.addAttachment(filename, source);
		}
	    mailSender.send(message);
		return "result";
	}
}
