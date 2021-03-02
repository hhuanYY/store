package cn.huan.t_store;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.unit.DataSize;



@SpringBootApplication
@EnableScheduling
@MapperScan("cn.huan.t_store.mapper")
public class TStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TStoreApplication.class, args);
	}
	
	@Bean
	public MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		
		// 当前项目中，无论上传的是什么，都不允许超过100M，否则直接报错，控制器根本就不执行
		DataSize dataSize = DataSize.ofMegabytes(100);
		factory.setMaxFileSize(dataSize);
		factory.setMaxRequestSize(dataSize);
		
		return factory.createMultipartConfig();
	}

}
