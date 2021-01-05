package JDBCCode;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class MainJDBC {

	public static void main(String[] args) {

	}

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null, rs2 = null;

	public Connection check_connection() {
		String username = "postgres";
		String password = "sudo";
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password);

		} catch (Exception e) {
		}
		return connection;
	}

	public String[] show_tables() {
		connection = check_connection();
		String[] result = new String[20];
		try {

			DatabaseMetaData dbmd = connection.getMetaData();
			String[] types = { "TABLE" };
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			int i = 0;
			while (rs.next()) {
				result[i] = (rs.getString("TABLE_NAME"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public void insert_data(String stockname, double stockquantity, String buyingdate, double buyingprice,
			String sellingdate, double sellingprice, double profitloss) {
		connection = check_connection();

		try {

			String query = "INSERT INTO stocktable (stock_name,stock_quantity,buying_date,buying_price,selling_date,selling_price,profit_loss) "
					+ "VALUES " + "('" + stockname + "','" + stockquantity + "','" + buyingdate + "', " + buyingprice
					+ ", '" + sellingdate + "'," + sellingprice + "," + profitloss + ")";

			statement = connection.createStatement();
			statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Stock added Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ResultSet fetch_data() {
		connection = check_connection();
		try {
			// we can add where to get perticular entry
			String query = "select * from stocktable";
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void edit_data(int stockid, String field, Date dt, String value) {
		String query = "";
		double temp = 0;
		try {
			switch (field) {
			case "Stock Name":
				query = "update stocktable set stock_name='" + value + "' where stock_id= '" + stockid + "'";
				break;
			case "Stock Quantity":
				temp = Double.valueOf(value);
				query = "update stocktable set stock_quantity='" + temp + "' where stock_id= '" + stockid + "'";
				break;
			case "Buying Date":
				query = "update stocktable set buying_date='" + dt + "' where stock_id= '" + stockid + "'";
				break;
			case "Buying Price":
				temp = Double.valueOf(value);
				query = "update stocktable set buying_price='" + temp + "' where stock_id= '" + stockid + "'";
				break;
			case "Selling Date":
				query = "update stocktable set selling_date='" + dt + "' where stock_id= '" + stockid + "'";
				break;
			case "Selling Price":
				temp = Double.valueOf(value);
				query = "update stocktable set selling_price='" + temp + "' where stock_id= '" + stockid + "'";
				break;
			default:
				break;

			}
			statement = connection.createStatement();
			statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Changes saved Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove_stock(String val) {
		connection = check_connection();
		try {

			String query = "delete from stocktable where stock_name='" + val + "'";

			statement = connection.createStatement();
			statement.executeUpdate(query);

			JOptionPane.showMessageDialog(null, "Changes saved successfully");
			fetch_data();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String get_investmoney() {
		double sum = 0;
		String str = "";
		try {
			String query = "SELECT SUM(buying_price)FROM stocktable ";
			statement = connection.createStatement();
			rs2 = statement.executeQuery(query);
			while (rs2.next()) {
				double c = rs2.getDouble(1);
				sum = sum + c;
				str = Double.toString(sum);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;

	}

	public double get_profitlossmoney() {
		double sum = 0;

		try {
			String query = "SELECT SUM(profit_loss)FROM stocktable ";
			statement = connection.createStatement();
			rs2 = statement.executeQuery(query);
			while (rs2.next()) {
				double c = rs2.getDouble(1);
				sum = sum + c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	public void delete_table() {
		try {
			// to delete whole table[drop table tablename]
			String query = "TRUNCATE TABLE stocktable  RESTART IDENTITY";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null, "Cleared table Successfully");
			fetch_data();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void create_csv(String filename) {
		try {
			String query = "COPY stocktable TO 'C:\\Users\\Public\\" + filename + ".csv' CSV HEADER";
			statement = connection.createStatement();
			statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null,
					"CSV file successfully created at C:\\Users\\Public\\" + filename + ".csv");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
