package cn.cld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("upload")
public class uploadController {


    @RequestMapping(value = "up", method=RequestMethod.POST, headers="Content-Type=multipart/form-data")
    @ResponseBody
    public String userList(@RequestParam("file") MultipartFile file,HttpServletRequest request, Model model){
        System.out.println("shangchaun");
        return "{1,1}";
    }
}
