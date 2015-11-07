package com.giong.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.giong.model.Theme;

@ManagedBean(name = "themesBean")
@ApplicationScoped
public class ThemesBean {
	
	private List<Theme> themes;
	
	@PostConstruct
	public void init() {
		this.themes = this.getThemes();
	}
	
	public List<Theme> getThemes() {
		this.themes = new ArrayList<Theme>();
		this.themes.add(new Theme(0, "Afterdark", "afterdark"));
		this.themes.add(new Theme(1, "Afternoon", "afternoon"));
		this.themes.add(new Theme(2, "Afterwork", "afterwork"));
		this.themes.add(new Theme(3, "Aristo", "aristo"));
		this.themes.add(new Theme(4, "Black-Tie", "black-tie"));
		this.themes.add(new Theme(5, "Blitzer", "blitzer"));
		this.themes.add(new Theme(6, "Bluesky", "bluesky"));
		this.themes.add(new Theme(7, "Bootstrap", "bootstrap"));
		this.themes.add(new Theme(8, "Casablanca", "casablanca"));
		this.themes.add(new Theme(9, "Cupertino", "cupertino"));
		this.themes.add(new Theme(10, "Cruze", "cruze"));
		this.themes.add(new Theme(11, "Dark-Hive", "dark-hive"));
		this.themes.add(new Theme(12, "Delta", "delta"));
		this.themes.add(new Theme(13, "Dot-Luv", "dot-luv"));
		this.themes.add(new Theme(14, "Eggplant", "eggplant"));
		this.themes.add(new Theme(15, "Excite-Bike", "excite-bike"));
		this.themes.add(new Theme(16, "Flick", "flick"));
		this.themes.add(new Theme(17, "Glass-X", "glass-x"));
		this.themes.add(new Theme(18, "Home", "home"));
		this.themes.add(new Theme(19, "Hot-Sneaks", "hot-sneaks"));
		this.themes.add(new Theme(20, "Humanity", "humanity"));
		this.themes.add(new Theme(21, "Le-Frog", "le-frog"));
		this.themes.add(new Theme(22, "Midnight", "midnight"));
		this.themes.add(new Theme(23, "Mint-Choc", "mint-choc"));
		this.themes.add(new Theme(24, "Overcast", "overcast"));
		this.themes.add(new Theme(25, "Pepper-Grinder", "pepper-grinder"));
		this.themes.add(new Theme(26, "Redmond", "redmond"));
		this.themes.add(new Theme(27, "Rocket", "rocket"));
		this.themes.add(new Theme(28, "Sam", "sam"));
		this.themes.add(new Theme(29, "Smoothness", "smoothness"));
		this.themes.add(new Theme(30, "South-Street", "south-street"));
		this.themes.add(new Theme(31, "Start", "start"));
		this.themes.add(new Theme(32, "Sunny", "sunny"));
		this.themes.add(new Theme(33, "Swanky-Purse", "swanky-purse"));
		this.themes.add(new Theme(34, "Trontastic", "trontastic"));
		this.themes.add(new Theme(35, "UI-Darkness", "ui-darkness"));
		this.themes.add(new Theme(36, "UI-Lightness", "ui-lightness"));
		this.themes.add(new Theme(37, "Vader", "vader"));
		return this.themes;
	}
}
