package com.springboot.email;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class maincontroller {

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${recaptcha.secret}")
	private String recaptchaSecret;
	
	@Value("${recaptcha.url}")
	private String recaptchaUrl;
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
		return builder.build();
	}
	
	@Autowired
	public RestTemplate restTemplate;
	
	
	@RequestMapping(value = "/")
	public String index()
	{
		return "contactform";
	}
	
	@RequestMapping(value = "/contact" , method = RequestMethod.POST )
	public String contact(HttpServletRequest request, @RequestParam("attachment") MultipartFile fi, HttpServletResponse response) throws MessagingException, IOException { 
		
		
		String gRecatchaResponse = request.getParameter("g-recaptcha-response");
		//verifyReCAPTCHA(gRecatchaResponse);
		//System.out.println(gRecatchaResponse);
		if(!verifyReCAPTCHA(gRecatchaResponse))
		{
              response.sendError(HttpServletResponse.SC_BAD_REQUEST);		
		}
		
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

	private boolean verifyReCAPTCHA(String gRecatchaResponse) {
		// TODO Auto-generated method stub
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("secret", recaptchaSecret);
		map.add("response", gRecatchaResponse);
		
		System.out.println(recaptchaSecret);
		System.out.println(recaptchaUrl);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,headers);
		//System.out.println("ok");
		//ResponseEntity<String> response  =restTemplate.postForEntity(recaptchaUrl, request, String.class);
		RecaptchaResponse res = restTemplate.postForObject(recaptchaUrl, request, RecaptchaResponse.class);
		System.out.println(res.isSuccess());
		System.out.println(res.getHostname());
		System.out.println(res.getChallenge_ts());
		if(res.getErrorCodes() != null)
		{
			for(String str : res.getErrorCodes())
				System.out.println(str);
		}
		return res.isSuccess();
	}
}
