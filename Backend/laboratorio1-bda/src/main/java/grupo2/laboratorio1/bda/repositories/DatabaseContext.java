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
                try{
                    User user = (User) authentication.getPrincipal();
                    String userId = user.getId().toString();
                    connection.createQuery("SELECT set_config('app.user.id', :user_id, false)")
                            .addParameter("user_id", userId)
                            .executeScalar(String.class);
                }catch (Exception e){}
                return connection;
            }
        };
    }
}
