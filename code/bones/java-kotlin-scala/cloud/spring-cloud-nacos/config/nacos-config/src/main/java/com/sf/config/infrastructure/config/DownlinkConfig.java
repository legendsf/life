package com.sf.config.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@ConfigurationProperties(prefix = "msg")
public class DownlinkConfig {
    private HashMap<String, String> pool = new HashMap<>();
 
	public HashMap<String, String> getPool() {
		return pool;
	}
 
}
