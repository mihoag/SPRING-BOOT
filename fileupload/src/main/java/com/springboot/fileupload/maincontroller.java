package com.springboot.fileupload;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class maincontroller {

	@Autowired
	private CustomerService ser;
	
	@RequestMapping(value = "/")
	public String home(Model model)
	{
		List<customer> arr = ser.findAll();
		model.addAttribute("listCustomers", arr);
		return "index";
	}
	
	@RequestMapping(value = "/new")
    public String newCustomer(Model model)
    {
		model.addAttribute("customer", new customer());
		return "newform";
    }
	@PostMapping(value = "/save")
	public String saveCustomer(@ModelAttribute("customer") customer c, @RequestParam("image") MultipartFile multipartFile) throws IOException
	{
		//System.out.println("ok");
		Path path = Paths.get("user-photos");
		System.out.println(path.toFile().getAbsolutePath());
		 String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 c.setPhoto(fileName);
		 customer saveCus =  ser.save(c);
		 saveCus.setPhotosImagePath("/user-photos/" + saveCus.getId() + "/" + saveCus.getPhoto());
		 ser.save(saveCus);
		 System.out.println(saveCus.PhotosImagePath);
		 String uploadDir = "user-photos/" + saveCus.getId();
		 FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/";
	}
	
	
}
