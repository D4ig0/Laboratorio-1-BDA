package grupo2.laboratorio1.bda.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

@Configuration
public class DatabaseContext {
    private String URL = "jdbc:postgresql://localhost:5432/desastresdb";
    private String USER = "postgres";
    private String PASSWORD;

    @Bean
    public Sql2o sql2o(){
        return new Sql2o(URL, USER, PASSWORD);
    }
}
