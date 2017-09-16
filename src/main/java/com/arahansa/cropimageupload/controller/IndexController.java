package com.arahansa.cropimageupload.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Slf4j
@Controller
public class IndexController {

    private static final String FILE2 = "file";
    private static final String UPLOADIMG = "/static/uploadimg/";
    @Autowired
    ServletContext servletContext;

    @GetMapping("/")
    public String index2(){
        log.info("hi2");
        return "index";
    }

    @Autowired
    ObjectMapper objectMapper;

    @lombok.Data
    @AllArgsConstructor
    static class ReturnData{
        String data;
    }

    /** 에디터에서 받는 이미지 업로드 처리 부분^^ */
    // 아, 우선 한글 처리 시.. (new String(파일명.getBytes(),"UTF-8") 이거 안해줘도 되는 것같다..)
    @RequestMapping(value = "/imageupload", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public ReturnData imageUpload(MultipartHttpServletRequest request) throws IOException
    {

        log.info("이미지 업로드 들어옴...");

        // 01. 리퀘스트에서 멀티파트파일을 받아서
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        List<MultipartFile> list = multiFileMap.get(FILE2);
        MultipartFile multipartFile = list.get(0);
        log.debug(multipartFile.getOriginalFilename());

        // 02. 파일을 전송하고
        String webappRoot = servletContext.getRealPath("/");
        String filename = multipartFile.getOriginalFilename();
        File file = new File("C:\\testImage\\" + filename);
        multipartFile.transferTo(file);

        // 03. 마지막에 최종 주소를 반환한다.
        // requet.getServername 을 하니, ajax에서 보내는 값이 리퀘스트 정보에 안떠서 InetAddress로
        // 받았다.
        String localIP = InetAddress.getLocalHost().getHostAddress();
        // http://를 붙여줘야 에디터 창에서 불러올 수가 있다. 음.. 자바스크립트내에서 붙일까? 일단 그냥 적자.
        return new ReturnData("http://" + localIP+ ":" + request.getServerPort() + filename);
    }

    // --------------------------------------------------------------
}
