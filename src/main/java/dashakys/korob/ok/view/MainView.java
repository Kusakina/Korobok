package dashakys.korob.ok.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.Profile;
import dashakys.korob.ok.repository.ProfileRepository;

@Route
public class MainView extends VerticalLayout {

	private final ProfileRepository profileRepository;
	final Grid<Profile> profileGrid;

	public MainView(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
		this.profileGrid = new Grid<>(Profile.class);
		add(profileGrid);
		listProfiles();
	}

	private void listProfiles() {
		profileGrid.setItems(profileRepository.findAll());
	}

}