package co.com.ias.certification.backend.configuration.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
//    @Profile({"test", "dev", "prod"})
    public DataSource testDatasource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:scripts/schema.sql")
                .build();
    }

//    @Bean
//    @Profile({"dev", "prod"})
//    public DataSource hikariDatasource(DatabaseCredentials credentials) {
//        HikariConfig config = new HikariConfig();
//        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//        config.addDataSourceProperty("databaseName", credentials.getDatabase());
//        config.addDataSourceProperty("portNumber", credentials.getPort());
//        config.addDataSourceProperty("serverName", credentials.getHost());
//        config.setUsername(credentials.getUsername());
//        config.setPassword(credentials.getPassword());
//        return new HikariDataSource(config);
//    }
}
