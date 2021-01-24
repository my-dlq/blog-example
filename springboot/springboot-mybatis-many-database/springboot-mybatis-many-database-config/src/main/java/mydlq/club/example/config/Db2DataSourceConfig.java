package mydlq.club.example.config;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据库二数据源配置
 *
 * @author mydlq
 */
@Configuration
@MapperScan(basePackages = Db2DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "db2SqlSessionFactory")
public class Db2DataSourceConfig {

    /** 指定 Sql Session Factory 的 Bean 名称 */
    static final String SQL_SESSION_FACTORY = "db2SqlSessionFactory";
    /** 指定 Mapper 类的包路径 */
    static final String PACKAGE = "mydlq.club.example.dao.db2";
    /** 指定数据库 Mapper 对应的 xml 文件路径 */
    static final String MAPPER = "classpath:mappers/db2/*.xml";

    /**
     * 配置数据源，设置为 hikari
     * @return DataSource
     */
    @Bean(name = "db2DataSource")
    @ConfigurationProperties("datasource.db2")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 数据源事务管理器
     * @return 数据源事务管理器
     */
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("db2DataSource") DataSource masterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Db2DataSourceConfig.MAPPER));
        return sessionFactory.getObject();
    }

}