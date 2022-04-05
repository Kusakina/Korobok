package dashakys.korob.ok;

import java.util.Scanner;

import dashakys.korob.ok.model.Credentials;
import dashakys.korob.ok.model.DatabaseEntity;
import dashakys.korob.ok.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final ProfileService profileService;
	private final CredentialsService credentialsService;

	@Override
	public void run(String... args) {
		System.out.println("Hello world!");

		testProfile();
	}

	<T extends DatabaseEntity> void printEntities(String caseName, String entitiesName, EntityService<T> service) {
		var allEntities = service.findAll();
		System.out.printf("%s: %d %s\n", caseName, allEntities.size(), entitiesName);
		for (T entity : allEntities) {
			System.out.println(entity);
		}
	}

	void printProfiles(String caseName) {
		printEntities(caseName, "профилей", profileService);
		printEntities(caseName, "реквизитов для входа", credentialsService);
	}

	void testProfile() {
		printProfiles("В начале");

		try (Scanner in = new Scanner(System.in)) {
			testSignUp(in);

			testSignIn(in);

			testRemove(in);
		}
	}

	String readString(String name, Scanner in) {
		System.out.printf("Введите %s: ", name);
		return in.nextLine();
	}

	void testSignUp(Scanner in) {
		int iterations = 3;
		System.out.printf("Делаем %d попыток регистрации\n", iterations);

		for (int it = 0; it < iterations; ++it) {
			String name = readString("имя", in);
			String login = readString("логин", in);
			String password = readString("пароль", in);

			try {
				credentialsService.signUp(name, login, password);
				System.out.println("Зарегистрировался: " + profileService.getSelectedProfile());
			} catch (EntityServiceException e) {
				System.out.println(e.getMessage());
			}

			printProfiles("После регистрации");
		}
	}

	void testSignIn(Scanner in) {
		int iterations = 3;
		System.out.printf("Делаем %d попыток входа\n", iterations);

		for (int it = 0; it < iterations; ++it) {
			String login = readString("логин", in);
			String password = readString("пароль", in);

			try {
				credentialsService.signIn(login, password);
				System.out.println("Залогинился: " + profileService.getSelectedProfile());
			} catch (EntityServiceException e) {
				System.out.println(e.getMessage());
			}

			printProfiles("После логина");
		}
	}

	void testRemove(Scanner in) {
		int iterations = 2;
		System.out.printf("Делаем %d попыток удаления\n", iterations);

		for (int it = 0; it < iterations; ++it) {
			String login = readString("логин", in);

			try {
				credentialsService.findByLogin(login)
						.map(Credentials::getProfile)
						.ifPresentOrElse(
							profileService::remove,
							() -> { throw new EntityServiceException("Профиль не найден"); }
						);
				System.out.println("Удалился: " + login);
			} catch (EntityServiceException e) {
				System.out.println(e.getMessage());
			}

			printProfiles("После удаления");
		}
	}
}
