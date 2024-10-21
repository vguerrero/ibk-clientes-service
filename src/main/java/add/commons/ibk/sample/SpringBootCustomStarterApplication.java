package add.commons.ibk.sample;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={
		"add.commons.ibk.sample",
})
public class SpringBootCustomStarterApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCustomStarterApplication.class, args);
	}


}
