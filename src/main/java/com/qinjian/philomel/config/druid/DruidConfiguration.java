package com.qinjian.philomel.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {

	@Bean
	public ServletRegistrationBean statViewServlet(){
		//创建servlet注册实体
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		//设置ip白名单
		servletRegistrationBean.addInitParameter("allow","127.0.0.1");
		//设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
		//servletRegistrationBean.addInitParameter("deny","127.0.0.1");
		//设置控制台管理用户
		servletRegistrationBean.addInitParameter("loginUsername","druid");
		servletRegistrationBean.addInitParameter("loginPassword","123456");
		//是否可以重置数据
		servletRegistrationBean.addInitParameter("resetEnable","false");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean statFilter(){
		//创建过滤器
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		//设置过滤器过滤路径
		filterRegistrationBean.addUrlPatterns("/*");
		//忽略过滤的形式
		filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

	//解决 spring.datasource.filters=stat,wall,log4j 无法正常注册进去
	@Component
	@ConfigurationProperties/*(prefix="spring.datasource")*/
	class IDataSourceProperties {
		@Value("${spring.datasource.url}")
		private String url;
		@Value("${spring.datasource.username}")
		private String username;
		@Value("${spring.datasource.password}")
		private String password;
		@Value("${spring.datasource.driverClassName}")
		private String driverClassName;
		@Value("${spring.datasource.initialSize}")
		private int initialSize;
		@Value("${spring.datasource.minIdle}")
		private int minIdle;
		@Value("${spring.datasource.maxActive}")
		private int maxActive;
		@Value("${spring.datasource.maxWait}")
		private int maxWait;
		@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
		private int timeBetweenEvictionRunsMillis;
		@Value("${spring.datasource.minEvictableIdleTimeMillis}")
		private int minEvictableIdleTimeMillis;
		@Value("${spring.datasource.validationQuery}")
		private String validationQuery;
		@Value("${spring.datasource.testWhileIdle}")
		private boolean testWhileIdle;
		@Value("${spring.datasource.testOnBorrow}")
		private boolean testOnBorrow;
		@Value("${spring.datasource.testOnReturn}")
		private boolean testOnReturn;
/*		@Value("${spring.datasource.poolPreparedStatements}")
		private boolean poolPreparedStatements;
		@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
		private int maxPoolPreparedStatementPerConnectionSize;*/
		@Value("${spring.datasource.filters}")
		private String filters;
		/*@Value("${spring.datasource.connectionProperties}")
		private String connectionProperties;*/

		@Bean     //声明其为Bean实例
		@Primary  //在同样的DataSource中，首先使用被标注的DataSource
		public DataSource dataSource() {
			DruidDataSource datasource = new DruidDataSource();
			datasource.setUrl(url);
			datasource.setUsername(username);
			datasource.setPassword(password);
			datasource.setDriverClassName(driverClassName);

			//configuration
			datasource.setInitialSize(initialSize);
			datasource.setMinIdle(minIdle);
			datasource.setMaxActive(maxActive);
			datasource.setMaxWait(maxWait);
			datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			datasource.setValidationQuery(validationQuery);
			datasource.setTestWhileIdle(testWhileIdle);
			datasource.setTestOnBorrow(testOnBorrow);
			datasource.setTestOnReturn(testOnReturn);
			//datasource.setPoolPreparedStatements(poolPreparedStatements);
			//datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
			try {
				datasource.setFilters(filters);
			} catch (SQLException e) {
				System.err.println("druid configuration initialization filter: " + e);
			}
			//datasource.setConnectionProperties(connectionProperties);
			return datasource;
		}

	}
}