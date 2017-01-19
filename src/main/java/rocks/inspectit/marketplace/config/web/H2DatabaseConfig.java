package rocks.inspectit.marketplace.config.web;

import org.dbunit.DatabaseUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Spring Boot provides a default embedded database, usually h2, but since we want to populate the database on
 * application start, we have to create our custom configuration
 *
 * @author NKO
 * @version %I%, %G%
 * @since 1.0.4-SNAPSHOT
 */
@Profile("h2")
@Configuration
public class H2DatabaseConfig {

	/**
	 * setter injection necessary,
	 * because constructor injection fails with {@link NoSuchMethodException}
	 */
	@SuppressWarnings("PMD")
	@Autowired
	private DataSource dataSource;
//	@Value("${h2.database.web.port}")
//	private String webPort;
//	@Value("${h2.database.tcp.port}")
//	private String tcpPort;

	/**
	 * not needed yet!
	 */
	// @Autowired
	// public H2DatabaseConfig(DataSource dataSource, @Value("${h2.database.web.port}") final String webPort,
	//     @Value("${h2.database.tcp.port}") final String tcpPort) {
	//   this.dataSource = dataSource;
	//   this.webPort = webPort;
	//   this.tcpPort = tcpPort;
	// }

	/**
	 * populating simple h2 in-Memory Database, with values from <b>smoke-db</b>, which can be accessed through URL
	 * <p>
	 * <p>
	 * not needed, yet.
	 * consider implementation, when writing integration or unit tests
	 * </p>
	 *
	 * @throws DatabaseUnitException if connection can't be established
	 * @throws SQLException          if DatabaseOperation fails
	 * @PostConstruct public void populateDatabaseWithSmokeData() throws DatabaseUnitException, SQLException {
	 * final DatabaseConnection connection = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
	 * final IDataSet dataSet = new FlatXmlDataSetBuilder()
	 * .build(Thread.currentThread().getContextClassLoader().getResource("database/schema.sql"));
	 * DatabaseOperation.REFRESH.execute(connection, dataSet);
	 * }
	 */
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server h2WebServer() throws SQLException {
//		return Server.createWebServer("-web", "-webAllowOthers", "-webPort", webPort);
//	}
//
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server h2TcpServer() throws SQLException {
//		return Server.createWebServer("-tcp", "-tcpAllowOthers", "-tcpPort", tcpPort);
//	}

}
