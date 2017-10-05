package lu.lllc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lu.lllc.Book;
import lu.lllc.DBInfo;

public class DBTools {

	public DBTools() {
		try {

			Class.forName(DBInfo.getDriver());
		} catch (ClassNotFoundException e) {
			System.out.println("Error. Driver class not found: " + e);
		}
	}

	Connection getConnction() {
		String dbURL = DBInfo.getDBURL();
		String user = DBInfo.getUser();
		String password = DBInfo.getPassword();
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(dbURL, user, password);

		} catch (SQLException e) {
			System.out.println("Error. Connection problem: " + e);

		}
		return connection;

	}

	ArrayList<Book> getAllBooksList() {
		ArrayList<Book> bookList = new ArrayList<Book>();
		Statement statement = null;
		ResultSet result = null;

		Connection connection = getConnction();

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Error. Can not create the statement: " + e);
			return null;
		}

		String searchString = "SELECT * FROM booksWithAuthor";

		try {
			result = statement.executeQuery(searchString);
		} catch (SQLException e) {
			System.out.println("Error. Problem with executeUpdate: " + e);
			return null;
		}

		try {
			while (result.next()) {
				Book book = new Book();
				long id = result.getLong("id");
				book.setId(id);

				long authorId = result.getLong("authorId");
				book.setAuthorId(authorId);
				
				Author author = getAuthor(authorId);
				book.setAuthorFirstName(author.getFirstName());
				book.setAuthorLastName(author.getLastName());
				

				String title = result.getString("title");
				book.setTitle(title);

				String description = result.getString("description");
				book.setDescription(description);

				float price = result.getFloat("price");
				book.setPrice(price);
				bookList.add(book);
			}
		} catch (SQLException e) {
			System.out.println("Error. Problem reading data: " + e);
		}

		try {
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bookList;

	}
	
	ArrayList<Cita> getAllCitaList() {
		ArrayList<Cita> citaList = new ArrayList<Cita>();
		Statement statement = null;
		ResultSet result = null;

		Connection connection = getConnction();

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Error. Can not create the statement: " + e);
			return null;
		}

		String searchString = "SELECT * FROM citas";

		try {
			result = statement.executeQuery(searchString);
		} catch (SQLException e) {
			System.out.println("Error. Problem with executeUpdate: " + e);
			return null;
		}

		try {
			while (result.next()) {
				Cita cita = new Cita();
				long id = result.getLong("id");
				cita.setId(id);

				String establecimiento = result.getString("establecimiento");
				cita.setEstablecimiento(establecimiento);
				
				String tipoDocumento = result.getString("tipoDocumento");
				cita.setTipoDocumento(tipoDocumento);
				
				long numDocumento = result.getLong("numDocumento");
				cita.setNumDocumento(numDocumento);				

				String apellidoPaterno = result.getString("apellidoPaterno");
				cita.setApellidoPaterno(apellidoPaterno);

				String consultaSolicitada = result.getString("consultaSolicitada");
				cita.setConsultaSolicitada(consultaSolicitada);

				citaList.add(cita);
			}
		} catch (SQLException e) {
			System.out.println("Error. Problem reading data: " + e);
		}

		try {
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return citaList;

	}

	// Here we use the PreparedStatement because the data come from the user
	void addNewBookAndAuthor(String title, String description, float price,
			String firstName, String lastName) {

		Connection connection = getConnction();

		try {
			PreparedStatement statement1 = connection.prepareStatement(
					"INSERT INTO authors (firstname, lastname) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);

			connection.setAutoCommit(false);
			statement1.setString(1, firstName);
			statement1.setString(2, lastName);

			statement1.executeUpdate();

			ResultSet res = statement1.getGeneratedKeys();
			// We do not need the loop. There is only one key generated

			res.next();
			long authorid = res.getLong(1);

			PreparedStatement statement2 = connection
					.prepareStatement("INSERT INTO booksWithAuthor (authorid, title, description, price) VALUES (?,?,?,?)");

			statement2.setLong(1, authorid);
			statement2.setString(2, title);
			statement2.setString(3, description);
			statement2.setFloat(4, price);

			statement2.executeUpdate();

			// we commit the update
			connection.commit();

			statement1.close();
			statement2.close();
			connection.close();

		} catch (SQLException e) {
			try {
				if (connection != null)
					// In case of an error we rollback the update
					connection.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("Error when adding a new book " + e);

		}

	}
	
	// Here we use the PreparedStatement because the data come from the user
	void addCita(String establecimiento, String tipoDocumento, long numDocumento,
			String apellidoPaterno, String consultaSolicitada) {

		Connection connection = getConnction();

		try {
			PreparedStatement statement1 = connection.prepareStatement(
					"INSERT INTO citas (establecimiento, tipoDocumento, numDocumento, "
					+ "apellidoPaterno, consultaSolicitada) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			connection.setAutoCommit(false);
			statement1.setString(1, establecimiento);
			statement1.setString(2, tipoDocumento);
			statement1.setLong(3, numDocumento);
			statement1.setString(4, apellidoPaterno);
			statement1.setString(5, consultaSolicitada);

			statement1.executeUpdate();

			// we commit the update
			connection.commit();

			statement1.close();
			connection.close();

		} catch (SQLException e) {
			try {
				if (connection != null)
					// In case of an error we rollback the update
					connection.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("Error when adding a new book " + e);

		}

	}

	Author getAuthor(long id) {

		Author author = new Author();
		PreparedStatement statement = null;
		ResultSet result = null;

		Connection connection = getConnction();

		try {
			statement = connection
					.prepareStatement("SELECT * FROM authors WHERE id=?");

			statement.setLong(1, id);
			result = statement.executeQuery();
			//Warning result can be null - in real application thing about it
			result.next();

			author.setId(result.getLong("id"));
			author.setFirstName(result.getString("firstname"));
			author.setLastName(result.getString("lastname"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e1) {
			}
		}

		return author;

	}

}
