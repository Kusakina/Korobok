package dashakys.korob.ok.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import dashakys.korob.ok.model.*;
import dashakys.korob.ok.repository.ProfileRepository;
import dashakys.korob.ok.repository.ProfileRoleRepository;
import dashakys.korob.ok.service.CredentialsService;
import dashakys.korob.ok.service.EntityServiceException;
import dashakys.korob.ok.service.ProfileService;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.util.Optional;

@Route
public class MainView extends VerticalLayout {

	private final ProfileRepository profileRepository;
	private final ProfileRoleRepository profileRoleRepository;
	CredentialsService credentialsService;
	//final Grid<Profile> profileGrid;
	final Grid<ProfileRole> profileRoleGrid;

	public MainView(ProfileRepository profileRepository, ProfileRoleRepository profileRoleRepository) {
	/*	TextField login = new TextField("Login");
		TextField password = new TextField("Password");
		Button loginButton = new Button("Login");
		layout.add*/
		this.profileRepository = profileRepository;
		this.profileRoleRepository = profileRoleRepository;
		//this.profileGrid = new Grid<>(Profile.class);
		this.profileRoleGrid = new Grid<>(ProfileRole.class);
		this.credentialsService = credentialsService;
		//add(profileGrid);
		add(profileRoleGrid);
		//setAdmin();
		listProfilesRole();

	}

	/*private void listProfiles() {
		profileGrid.setItems(profileRepository.findAll());
	}*/
	private void listProfilesRole() {
		profileRoleGrid.setItems(profileRoleRepository.findAll());
	}
	private void setAdmin() {
		int count_admin =0;

		for (ProfileRole profileRole :profileRoleRepository.findAll()){
			if(profileRole.getRole() == Role.ADMIN){
				count_admin++;
			}
			if(count_admin>0) break;
		}
		if (count_admin==0){
			String name = "admin";
			String login = "admin";
			String password = "admin";
			try {
				credentialsService.signUp(name, login, password);
			} catch (EntityServiceException e) {

			}
			profileRoleRepository.findByProfile(profileRepository.findByLogin("admin").get()).get().setRole(Role.ADMIN);
		}
		//profileGrid.setItems(profileRepository.findAll());
	}
	/*private void listCustomers(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			profileGrid.setItems(profileRepositoryo.findAll());
		}
		else {
			profileGrid.setItems(profileRepository.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}*/

}