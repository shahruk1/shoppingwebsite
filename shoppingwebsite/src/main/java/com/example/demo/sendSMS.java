package com.example.demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class sendSMS {
	
	public String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + URLEncoder.encode("NzI0ZjcxNDg1NDRiMzc0OTU2NTI2YjMzNDE1ODU0N2E=", "UTF-8");
			String message = "&message=" + URLEncoder.encode("Hi there, thank you for sending your first test message from Textlocal. See how you can send effective SMS campaigns here: https://tx.gl/r/2nGVj/", "UTF-8");
			String sender = "&sender=" + URLEncoder.encode("600010", "UTF-8");
			String numbers = "&numbers=" + URLEncoder.encode("8639181806", "UTF-8");
			
			// Send data
			String data = "https://api.textlocal.in/send/?" + apiKey + numbers + message + sender;
			URL url = new URL(data);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String sResult="";
			while ((line = rd.readLine()) != null) {
			// Process line...
				sResult=sResult+line+" ";
			}
			rd.close();
			
			return sResult;
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}