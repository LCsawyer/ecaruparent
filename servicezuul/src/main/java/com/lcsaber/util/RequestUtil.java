package com.lcsaber.util;

/**
 * @Author: LiChao
 * @Date: 2019/6/19 15:46
 */
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.*;
public class RequestUtil {
    private String headers = "{";
    private String requestBody = "";
    private String uri = "";
    private String userId = "";
    private String ip="";

    public RequestUtil(HttpServletRequest request) {
        uri = request.getRequestURI();
        Enumeration<String> headerNames = request.getHeaderNames();
        String name = "";
        ip = request.getRemoteAddr();
        while(headerNames.hasMoreElements()){
            name=headerNames.nextElement();
            headers+= "\""+name+"\":\""+request.getHeader(name)+"\",";
        }
        headers += "\"ip\":"+"\""+ip+"\",";
        String method = request.getMethod();
        headers+= "\"method\":\""+method+"\"}";
        try{
            BufferedReader br = request.getReader();
            String bstr = "";
            while((bstr=br.readLine())!=null){
                requestBody += bstr;
            }
            br.close();

        }catch (Exception e){}
        if (requestBody==""){
            requestBody = "\"\"";
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{\"userId\":"+userId+",\"uri\":\""+uri+"\",\"headers\":"+headers+",\"body\":"+requestBody+"}";
    }
}
