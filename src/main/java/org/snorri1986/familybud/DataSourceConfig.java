package org.snorri1986.familybud;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

  // PROD
  /*@Bean
  public DataSource getDataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:postgresql://c9tiftt16dc3eo.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com:5432/da3clq4fosdocj");
    dataSourceBuilder.username("udh6tl33aq5jhc");
    dataSourceBuilder.password("p66ad00211b0d03ceeb31b2b51ef3be8b11121153b837f0719b0ed2b9b65624f9");
    return dataSourceBuilder.build();
  }*/

  // DEV
  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:postgresql://cav8p52l9arddb.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com:5432/d537j7eaietli3");
    dataSourceBuilder.username("u4cg7fn2s82n4v");
    dataSourceBuilder.password("p7abbcc90302aeee435cc14a9a06cc74a76887800edc1924b87b274c53f5de1cf");
    return dataSourceBuilder.build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}
