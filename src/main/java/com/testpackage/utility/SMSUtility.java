package com.testpackage.utility;

import com.testpackage.model.StudentEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class SMSUtility {

    public static void sendSMSToStudent(StudentEntity student) {
        try {
            String passWordMessageString = "Hi," + student.getUsername() + ";Greeting of the day...!!!" + "Your current password is:" + student.getPassword() + ";Please Rest after Login";
            Date mydate = new Date(System.currentTimeMillis());
            String data = "";
            data += "sendMethod=simpleMsg";
            data += "&userId=" + student.getUsername();
            data += "&password=" + URLEncoder.encode(student.getPassword(), "UTF-8");
            data += "&msg=" + URLEncoder.encode(passWordMessageString, "UTF-8");
            data += "&mobile=" + URLEncoder.encode(student.getPhone(), "UTF-8");
            data += "&msgType=text";
            data += "&dltEntityId=8329256381";
            data += "&dltTemplateId=8329256381";
            data += "&format=json";
            URL url = new URL("http://www.smsgateway.center/SMSApi/rest/send?" + data);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            System.out.println(buffer.toString());
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}