package com.lcsaber.util;

/**
 * @Author: LiChao
 * @Date: 2019/7/1 9:52
 */
public class CodeData {
    public static final int SUCCESS = 1000; //查询成功
    public static final int NODATA = 1001; //查询成功无记录
    public static final int FEAILED = 2000; //查询失败
    public static final int ACCOUNT_ERROR = 2001; //账号不存在或被禁用
    public static final int API_NOT_EXISTS = 2002; //请求的接口不存在
    public static final int API_NOT_PER = 2003; //没有该接口的访问权限
    public static final int PARAMS_ERROR = 2004; //参数为空或格式错误
    public static final int API_DISABLE = 3001; //查询权限已被限制
    public static final int UNKNOW_IP = 3099; //非法IP请求
    public static final int SYSTEM_ERROR = 9999; //系统异常

}
