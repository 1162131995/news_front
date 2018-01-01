package cn.et.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.sql.Connection;

public class DbUtils {
	static Properties properties = new Properties();
	static {

		// 加载bin目录下的文件
		InputStream inStream = DbUtils.class.getResourceAsStream("/jdbc_mysql.properties");
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		// 获取properties中的数据
		String url = properties.getProperty("url");
		String driverClass = properties.getProperty("driverClass");
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");

		Connection connection = null;
		try {
			// jvm加载该类
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, userName, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static List<Map> query(String sql) {
		Connection connection = getConnection();
		PreparedStatement ps;
		List<Map> list = null;
		try {
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			list = new ArrayList();
			while (rs.next()) {
				Map map = new HashMap();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String name = rsmd.getColumnName(i);
					String value = rs.getString(i);
					map.put(name, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean execute(String sql) {
		Connection connection = getConnection();

		boolean bool = false;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}
}
