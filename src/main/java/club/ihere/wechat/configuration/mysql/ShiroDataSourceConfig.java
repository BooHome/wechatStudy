package club.ihere.wechat.configuration.mysql;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author: fengshibo
 * @date: 2018/11/6 10:59
 * @description:
 */
@Configuration
@MapperScan(basePackages = "club.ihere.wechat.mapper.shiro", sqlSessionTemplateRef = "shiroSqlSessionTemplate")
public class ShiroDataSourceConfig {
    @Bean(name = "shiroDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shiro")
    public DataSource setDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shiroTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("shiroDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "shiroSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("shiroDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/shiro/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "shiroSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("shiroSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
