package com.dayapp.view.page;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class IndexPage {

	private static final String URL = "http://localhost:8080/app";

	@FindBy(id = "inputYear")
	private SelenideElement inputYear;

	@FindBy(id = "inputMonth")
	private SelenideElement inputMonth;

	@FindBy(id = "inputDay")
	private SelenideElement inputDay;

	@FindBy(id = "addMonth")
	private SelenideElement addMonth;

	@FindBy(id = "selectDay")
	private SelenideElement selectDay;

	@FindBy(id = "calculateButton")
	private SelenideElement calculateButton;

	@FindBy(id = "businessDay")
	private SelenideElement businessDay;

	@FindBy(id = "searchId")
	private SelenideElement searchId;

	@FindBy(id = "reindexButton")
	private SelenideElement reindexButton;

	@FindBy(id = "newButton")
	private SelenideElement newButton;

	@FindBy(id = "serchButton")
	private SelenideElement serchButton;

	public static IndexPage open() {
		return Selenide.open(URL, IndexPage.class);
	}

	public String title() {
		return Selenide.title();
	}

	public IndexPage 全件表示する() {
		reindexButton.click();
		return page(IndexPage.class);
	}

	public IndexPage 検索IDは(String 検索ID) {
		searchId.setValue(検索ID);
		return page(IndexPage.class);
	}

	public IndexPage 検索する() {
		serchButton.click();
		return page(IndexPage.class);
	}

	public IndexPage 基準年は(String 基準年) {
		inputYear.setValue(基準年);
		return page(IndexPage.class);
	}

	public IndexPage 基準月は(String 基準月) {
		inputMonth.setValue(基準月);
		return page(IndexPage.class);
	}

	public IndexPage 基準日は(String 基準日) {
		inputDay.setValue(基準日);
		return page(IndexPage.class);
	}

	public IndexPage 業務月は(String 業務月) {
		addMonth.selectOption(業務月);
		return page(IndexPage.class);
	}

	public IndexPage 業務日は(String 業務日) {
		selectDay.selectOption(業務日);
		return page(IndexPage.class);
	}

	public IndexPage で計算する() {
		calculateButton.click();
		return page(IndexPage.class);
	}

	public SelenideElement 計算結果() {
		return $(By.cssSelector("#businessDay"));
	}

	public NewPage 新規登録画面へ遷移する() {
		newButton.click();
		return page(NewPage.class);
	}

	public EditPage 更新画面へ遷移する(int 行) {
		$(By.id(行 + "_" + "updateButton")).click();
		return page(EditPage.class);
	}

	public IndexPage 削除する(int 行) {
		$(By.id(行 + "_" + "deleteButton")).click();
		return page(IndexPage.class);
	}

	public SelenideElement 検索結果() {
		return $(By.cssSelector(".table tr"));
	}

	public int 検索結果の件数() {
		return $(By.cssSelector(".table")).findElements(By.tagName("tr")).size();
	}


}
