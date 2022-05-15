package dashakys.korob.ok;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import dashakys.korob.ok.model.*;
import dashakys.korob.ok.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@RequiredArgsConstructor
@Theme("my-app")
public class Application implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final ProfileService profileService;
	private final CredentialsService credentialsService;

	private final GameService gameService;
	private final ShopGameService shopGameService;
	private final PurchaseGameService purchaseGameService;
	private final PurchaseService purchaseService;

	@Bean
	public CommandLineRunner loadData() {
		return (args) -> {
			System.out.println("Hello world!");



			setAdmin();
			//addGame();

			/*
			try (Scanner in = new Scanner(System.in)) {
				testProfile(in);

				testGames(in);
			}
			*/
		};
	}

	private void setAdmin() {
		String login = "admin";

//		boolean noAdmin = profileService.findAllByRole(Role.ADMIN).isEmpty();
		boolean noAdmin = profileService.findByLogin(login).isEmpty();

		if (noAdmin) {
			String name = "admin";
			String password = "admin";
			credentialsService.register(name, login, password, Role.ADMIN);
		}
	}


	private void addGame(){

			var game2 = gameService.addGame("Шашки");
			int price2 = 700;
			int count2 = 3;

			shopGameService.addShopGame(game2, price2, count2);





	/*	for (int j =0;j<shopGameService.findAll().size();++j ){
			shopGameService.remove(shopGameService.findAll().get(j));
		}
		for (int j =0;j<gameService.findAll().size();++j ){
			gameService.remove(gameService.findAll().get(j));
		}*/
		//gameService.remove(gameService.findAll().);
		//Game a = gameService.findByName("Уно").get();
		//gameService.remove(a);
		//boolean noGame = shopGameService.findAll().isEmpty();
		//var game2 = gameService.addGame("Уно");
		//if(noGame) {
			//var game4 = gameService.addGame("Монополия");
			//shopGameService.addShopGame(game4, 2500, 2);

		//}
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

	void testProfile(Scanner in) {
		printProfiles("В начале");

		testSignUp(in);

		testSignIn(in);

		testRemove(in);
	}

	String readString(String name, Scanner in) {
		System.out.printf("Введите %s: ", name);
		return in.nextLine();
	}

	int readInt(String name, Scanner in) {
		return Integer.parseInt(readString(name, in));
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
		System.out.printf("Делаем %d попыток удаления профиля\n", iterations);

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

	void printGames(String caseName) {
		printEntities(caseName, "игр", gameService);
		printEntities(caseName, "игр в магазине", shopGameService);
	}

	void printPurchases(String caseName) {
		printEntities(caseName, "заказов", purchaseService);
		printEntities(caseName, "артикулов в заказах", purchaseGameService);
	}

	void testGames(Scanner in) {
		printGames("В начале");
		testAddGame(in);

		printPurchases("В начале");
		testAddPurchase(in);
		testPurchasedByProfile(in);
	}

	void testAddGame(Scanner in) {
		int iterations = 3;
		System.out.printf("Делаем %d попыток добавления игры\n", iterations);

		for (int it = 0; it < iterations; ++it) {
			try {
				String name = readString("название", in);
				var game = gameService.addGame(name);

				System.out.printf("Добавляем игру %s в магазин\n", game.getName());
				int price = readInt("цену", in);
				int count = readInt("кол-во", in);

				shopGameService.addShopGame(game, price, count);
			} catch (EntityServiceException e) {
				System.out.println(e.getMessage());
			}

			printGames("После добавления");
		}
	}

	Profile readProfile(String caseName, Scanner in) {
		String login = readString(String.format("логин %s", caseName), in);

		return credentialsService.findByLogin(login).map(Credentials::getProfile).orElseThrow(
				() -> new EntityServiceException(String.format("Пользователь с логином %s не найден", login))
		);
	}

	void testAddPurchase(Scanner in) {
		int iterations = 3;
		System.out.printf("Делаем %d попыток заказать игры\n", iterations);

		for (int it = 0; it < iterations; ++it) {
			try {
				var client = readProfile("покупателя", in);
				var manager = readProfile("продавца", in);

				Purchase purchase = new Purchase(client, manager);
				purchaseService.save(purchase);

				for (ShopGame shopGame : shopGameService.findAll()) {
					Game game = shopGame.getGame();
					int count = readInt(String.format("штук игры %s", game.getName()), in);
					purchaseGameService.addPurchaseGame(purchase, shopGame, count);
				}

				int cost = purchaseGameService.findAllByPurchase(purchase).stream()
						.mapToInt(PurchaseGame::getCost)
						.sum();

				purchase.setCost(cost);
				purchaseService.save(purchase);
			} catch (EntityServiceException e) {
				System.out.println(e.getMessage());
			}

			printPurchases("После покупки");
		}
	}

	void testPurchasedByProfile(Scanner in) {
		for (Profile profile : profileService.findAll()) {
			System.out.printf("Игры, заказанные пользователем %s\n", profile.getName());

			try {
				List<Game> games = gameService.findAllPurchasedByProfile(profile);
				System.out.printf("%d игр\n", games.size());
				for (Game game : games) {
					System.out.println(game.getName());
				}
			} catch (EntityServiceException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
