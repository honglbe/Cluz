package com.jc.cluz.driver.mouthpiece;

import java.util.Map;

/**
 * Created by honglb on 2018/7/6.
 */
public interface ApiActions {
    String sendGet(String url);
    String sendPost(String url, Map<String, String> parameters);
    String doGet(String url);
    String doPost(String url, Map params);
    String doJsonPost(String url, String params) throws Exception;

}
