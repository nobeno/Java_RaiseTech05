package com.dayapp.mapper;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.dayapp.domain.BusinessDays;

@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BusinessDaysMapperTest {

	@Autowired
	private BusinessDaysMapper target;

	@Test
	public void 全件検索して結果をリストで取得できる() throws Exception{
		List<BusinessDays> actual = target.selectAll();
		assertThat(actual.size()).isEqualTo(3);
	}

	@Test
	public void ID検索して1件だけ結果を取得できる() throws Exception{
		BusinessDays actual = target.selectByPrimaryKey(1);

		assertThat(((BusinessDays) actual).getId()).isEqualTo(1);
		assertThat(((BusinessDays) actual).getIdDays()).isEqualTo(2019110601);
		assertThat(((BusinessDays) actual).getName()).isEqualTo("会社名テスト1");
		assertThat(((BusinessDays) actual).getDay()).isEqualTo("業務日付テスト1");
	}

	@Test
	public void NULL検索して結果がNULLになる() throws Exception{
		BusinessDays actual = target.selectByPrimaryKey(null);

		assertThat(actual).isNull();
	}

	@Test
	public void 新規登録ができる() throws Exception{
		BusinessDays saveBusinessDays = new BusinessDays();
		saveBusinessDays.setId(4);
		saveBusinessDays.setIdDays(2019111001);
		saveBusinessDays.setName("会社名テスト4");
		saveBusinessDays.setDay("業務日付テスト4");

		target.insert(saveBusinessDays);
		BusinessDays actual = target.selectByPrimaryKey(4);

		assertThat(((BusinessDays) actual).getIdDays()).isEqualTo(2019111001);
		assertThat(((BusinessDays) actual).getName()).isEqualTo("会社名テスト4");
		assertThat(((BusinessDays) actual).getDay()).isEqualTo("業務日付テスト4");

	}

	@Test
	public void 既にあるキーの登録はDuplicateKeyExceptionになる() throws Exception{
		BusinessDays saveBusinessDays = new BusinessDays();
		saveBusinessDays.setId(1);
		saveBusinessDays.setIdDays(2019110601);
		saveBusinessDays.setName("会社名テスト1");
		saveBusinessDays.setDay("業務日付テスト1");

		assertThatThrownBy(() -> {
			target.insert(saveBusinessDays);
			}).isInstanceOf(DuplicateKeyException.class);
	}

	@Test
	public void キーに紐づく1件の削除ができる() throws Exception{
		target.deleteByPrimaryKey(3);

		List<BusinessDays> actual = target.selectAll();
		assertThat(actual.size()).isEqualTo(2);
	}

	@Test
	public void キーに紐づく1件を更新できる() throws Exception{
		BusinessDays saveBusinessDays = new BusinessDays();
		saveBusinessDays.setId(1);
		saveBusinessDays.setIdDays(2019110601);
		saveBusinessDays.setName("会社名テスト1-2");
		saveBusinessDays.setDay("業務日付テスト1-2");

		target.updateByPrimaryKeySelective(saveBusinessDays);

		BusinessDays actual = target.selectByPrimaryKey(1);

		assertThat(((BusinessDays) actual).getId()).isEqualTo(1);
		assertThat(((BusinessDays) actual).getIdDays()).isEqualTo(2019110601);
		assertThat(((BusinessDays) actual).getName()).isEqualTo("会社名テスト1-2");
		assertThat(((BusinessDays) actual).getDay()).isEqualTo("業務日付テスト1-2");
	}
}
