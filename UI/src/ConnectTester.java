import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class ConnectTester {

	@Test
	void testConnect() {
		Connection connection = null;
		connection = connect.dbConnector();
	}
}
