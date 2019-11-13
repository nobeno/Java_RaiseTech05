package com.dayapp.view;

import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeborne.selenide.Configuration;
import com.dayapp.view.page.EditPage;
import com.dayapp.view.page.IndexPage;
import com.dayapp.view.page.NewPage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestPropertySource(locations = "classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IndexViewTest {

	private IndexPage page;

	@BeforeClass
	public static void setUp() {
		Configuration.holdBrowserOpen = false;
	}

	@Before
	public void setUpTest() {
		page = IndexPage.open();
	}

	@Test
	public void ID検索で2019110601を入れて結果が一覧で取得できる() {
		IndexPage actual = page.検索IDは("2019110601").検索する();

		actual.検索結果().shouldBe(visible);
		assertThat(actual.検索結果の件数()).isEqualTo(2);
	}

	@Test
	public void 一覧画面から新規登録画面へ遷移できる() throws Exception {
		NewPage actual = page.新規登録画面へ遷移する();

		assertThat(actual.title()).isEqualTo("業務日付登録");
	}

	@Test
	public void 一覧画面から更新画面へ遷移できる() throws Exception {
		EditPage actual = page.更新画面へ遷移する(1);

		assertThat(actual.title()).isEqualTo("業務日付更新");
	}

	@Test
	public void 基準日20191112を入力して計算できる() throws Exception {
		IndexPage actual = page.基準年は("2019").基準月は("11").基準日は("12").業務月は("翌月").業務日は("末日").で計算する();

		assertThat(actual.計算結果().getText()).isEqualTo("2019年12月31日火曜日");
	}

	@Test
	public void 削除できる()throws Exception{
		page.削除する(4);

		IndexPage actual = page.全件表示する();
		actual.検索結果().shouldBe(visible);
		assertThat(actual.検索結果の件数()).isEqualTo(4);
	}

}
