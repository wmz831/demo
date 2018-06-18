package com.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by Wang Mingzhen om 2018/6/10
 */
@Controller
public class FileController {




    @RequestMapping("/uploadFile")
    public @ResponseBody String doUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("contentType:"+contentType+" - "+"fileName:"+fileName);
//        String filePath = request.getSession().getServletContext().getRealPath("/");
        String filePath = "D:/upFile/";
        try{
            FileUtil.upLoadFile(file.getBytes(),filePath,fileName);
        }catch (Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/ajaxUp")
    @ResponseBody
    public String ajaxUp(@RequestParam(value = "file",required = false) MultipartFile file){
        System.out.println(file.isEmpty());
        System.out.println(file.getName());
        System.out.println(file.getSize());
        return "";
    }


    @RequestMapping("/fileDownload")
    public void downLoad(HttpServletResponse resp) throws UnsupportedEncodingException {
        String filePath = "D:/upFile/";
        String fileName = "白底3寸.JPG";
        resp.setHeader("Content-Type","application/octet-stream");//Response
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf8");
        String encodingName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");//todo
        resp.setHeader("Content-Disposition","attachment;filename="+encodingName);

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os =null;
        try{
            os=resp.getOutputStream();
            bis= new BufferedInputStream(new FileInputStream(new File(filePath+fileName)));
            int i =bis.read(buff);
            while(i!=-1){
                os.write(buff, 0, buff.length);
                os.flush();
                i =bis.read(buff);
            }
            bis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //跳转
    @RequestMapping("/uploadimg")
    public String upLoading(){
        return "uploadimg";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
