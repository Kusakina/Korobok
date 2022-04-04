package dashakys.korob.ok;

import java.util.Scanner;

import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.service.GameService;
import dashakys.korob.ok.service.ProfileService;
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

	@Override
	public void run(String... args) {
		System.out.println("Hello world!");

		testProfile();
	}

	void testProfile() {

		var oldProfiles = profileService.findAll();
		System.out.println("Old: " + oldProfiles.size());

		Profile savedProfile;
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Введите имя профиля:");
			String name = in.nextLine();

			var profile = new Profile(name);
			savedProfile = profileService.save(profile);

			System.out.println("Исходный профиль: " + profile);
			System.out.println("Сохраненный профиль: " + savedProfile);
		}

		var newProfiles = profileService.findAll();
		System.out.println("New: " + newProfiles.size());
		for (Profile profile : newProfiles) {
			System.out.println(profile);
		}

		profileService.remove(savedProfile);

		var lastProfiles = profileService.findAll();
		System.out.println("Last: " + lastProfiles.size());
	}
}
