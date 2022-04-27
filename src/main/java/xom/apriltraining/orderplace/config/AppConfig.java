package xom.apriltraining.orderplace.config;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class AppConfig {


	public ObjectMapper objectMapper()
	{
		return new ObjectMapper();
	}
	
}
