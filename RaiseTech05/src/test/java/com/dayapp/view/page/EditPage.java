package com.dayapp.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class EditPage {

	@FindBy(id = "updateButton")
	private SelenideElement updateButton;

	@FindBy(id = "backButton")
	private SelenideElement backButton;

	@FindBy(id = "idDays")
	private SelenideElement idDays;

	@FindBy(id = "name")
	private SelenideElement name;

	@FindBy(id = "day")
	private SelenideElement day;

	public String title() {
		return Selenide.title();
	}

	public IndexPage 一覧画面へ戻る() {
		backButton.click();
		return page(IndexPage.class);
	}

	public EditPage 日付IDは(String ID) {
		idDays.setValue(ID);
		return page(EditPage.class);
	}

	public EditPage 会社名は(String 会社名) {
		name.setValue(会社名);
		return page(EditPage.class);
	}

	public EditPage 業務日付は(String 業務日付) {
		day.setValue(業務日付);
		return page(EditPage.class);
	}

	public IndexPage で更新する() {
		updateButton.click();
		return page(IndexPage.class);
	}
}
