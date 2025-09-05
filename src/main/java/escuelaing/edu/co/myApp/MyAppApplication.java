package escuelaing.edu.co.myApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);
	}
	@Bean
	CommandLineRunner showDb(MongoDatabaseFactory f, MongoTemplate t) {
		return args -> {
			System.out.println("➡️ DB en uso: " + f.getMongoDatabase().getName());
			t.executeCommand("{ ping: 1 }");
			System.out.println("✅ Ping Mongo OK");
		};
	}
}
