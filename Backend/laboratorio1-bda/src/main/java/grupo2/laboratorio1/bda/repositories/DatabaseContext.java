package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.authentication.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

@Configuration
public class DatabaseContext {
    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.user}")
    private String dbUser;

    @Value("${database.password}")
    private String dbPass;

    @Bean
    public Sql2o sql2o(){
        return new Sql2o(dbUrl, dbUser, dbPass){
            @Override
            public Connection open(){
                Connection connection = super.open();
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if(authentication != null){
                    User user = (User) authentication.getPrincipal();
                    Integer user_id = user.getId();
                    connection.createQuery("SELECT set_config('app.user.id', :user_id, false)")
                            .addParameter("user_id", user_id)
                            .executeScalar(String.class);
                }
                return connection;
            }
        };
    }
}
