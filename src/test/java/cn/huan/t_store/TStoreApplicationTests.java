package cn.huan.t_store;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TStoreApplicationTests {

	@Autowired
	private DataSource ds;

	@Test
	public void contextLoads() throws SQLException {
		System.err.println(ds.getConnection());
	}

}
