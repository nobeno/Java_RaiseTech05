package com.dayapp.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class NewPage {

	@FindBy(id = "backButton")
	private SelenideElement backButton;

	public String title() {
		return Selenide.title();
	}

	public IndexPage 一覧画面へ戻る() {
		backButton.click();
		return page(IndexPage.class);
	}
}
