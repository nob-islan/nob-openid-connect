package nob.example.opproject;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@MapperScan(basePackages = "nob.example.opproject.mapper")
@OpenAPIDefinition(info = @Info(title = "OpenID Provider API", version = "1.0.0", description = "OpenIDプロバイダの機能を提供するAPIです。"))
public class OpProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpProjectApplication.class, args);
	}

	// MyBatisの設定
	@Bean
	SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		// コンフィグファイルの読み込み
		sessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));

		return sessionFactory.getObject();
	}
}