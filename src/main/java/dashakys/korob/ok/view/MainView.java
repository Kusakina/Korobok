package dashakys.korob.ok.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.service.ProfileService;

@Route
public class MainView extends VerticalLayout {

	private final ProfileService profileService;
	final Grid<Profile> profileGrid;

	public MainView(ProfileService profileService) {
//		TextField login = new TextField("Login");
//		TextField password = new TextField("Password");
//		Button loginButton = new Button("Login");
//		layout.add
		this.profileService = profileService;
		this.profileGrid = new Grid<>(Profile.class);
		add(profileGrid);
		listProfiles();
	}

	private void listProfiles() {
		profileGrid.setItems(profileService.findAll());
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