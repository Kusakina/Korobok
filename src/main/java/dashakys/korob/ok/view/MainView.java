package dashakys.korob.ok.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.model.ShopGame;
import dashakys.korob.ok.service.ProfileService;
import dashakys.korob.ok.service.ShopGameService;

@Route
public class MainView extends VerticalLayout {

	private final ProfileService profileService;
	final Grid<Profile> profileGrid;

	private final ShopGameService shopGameService;
	final Grid<ShopGame> shopGameGrid;

	public MainView(ProfileService profileService, ShopGameService shopGameService) {
//		TextField login = new TextField("Login");
//		TextField password = new TextField("Password");
//		Button loginButton = new Button("Login");
//		layout.add
		this.profileService = profileService;
		this.profileGrid = new Grid<>(Profile.class);
		this.shopGameService = shopGameService;
		this.shopGameGrid = new Grid<>(ShopGame.class, false);

		shopGameGrid.addColumn(ShopGame::getGameName).setHeader("Игра").setSortable(true);
		shopGameGrid.addColumn(ShopGame::getPrice).setHeader("Цена").setSortable(true);
		shopGameGrid.addColumn(ShopGame::getCount).setHeader("Количество").setSortable(true);

		//this.shopGameGrid = new Grid<>(ShopGame.class);

		//add(profileGrid);
		add(shopGameGrid);

		listShopGames();
		//listProfiles();
	}

	private void listProfiles() {
		profileGrid.setItems(profileService.findAll());
	}
	private void listShopGames() {
		shopGameGrid.setItems(shopGameService.findAll());
	}

	/*private void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			profileGrid.setItems(profileRepository.findAll());
		}
		else {
			profileGrid.setItems(profileRepository.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}*/

}