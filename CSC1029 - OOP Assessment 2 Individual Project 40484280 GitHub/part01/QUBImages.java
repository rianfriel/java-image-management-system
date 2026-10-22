package part01;

import java.time.LocalDate;
import java.util.Scanner;

public class QUBImages {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		ImageManager manager = new ImageManager();

		// Load Default Images
		loadDefaultImages(manager);

		String options[] = { "Add Images", "Search", "Display All", "Exit" };

		Menu menu = new Menu("QUB Images", options);

		boolean running = true;

		while (running) {

			int choice = menu.getUserChoice();

			switch (choice) {
			case 1:
				addImage(manager, scanner);
				break;

			case 2:
				searchImages(manager, scanner);
				break;

			case 3:
				displayAll(manager);
				break;

			case 4:
				running = false;
				break;

			default:
				System.out.println("Invalid option. ");
			}
		}

		scanner.close();

	}

	// Load Default Images
	private static void loadDefaultImages(ImageManager manager) {

		manager.addImage(new ImageRecord(1, "Andromeda Galaxy", "Image of the Andromeda Galaxy.", ImageType.ASTRONOMY,
				LocalDate.of(2023, 1, 1), "Andromeda.png"));

		manager.addImage(new ImageRecord(2, "Lanyon QUB", "An image of the QUB Lanyon Building.",
				ImageType.ARCHITECTURE, LocalDate.of(2023, 2, 1), "LanyonQUB.png"));

		manager.addImage(new ImageRecord(3, "Kermit Plays Golf", "An image of Kermit the frog playing golf.",
				ImageType.SPORT, LocalDate.of(2023, 3, 1), "KermitGolf.png"));

		manager.addImage(new ImageRecord(4, "Mourne Mountains", "A panoramic view of the Mourne Mountains.",
				ImageType.LANDSCAPE, LocalDate.of(2023, 4, 1), "Mourne.png"));

		manager.addImage(new ImageRecord(5, "Homer Simpson", "Homer Simpson - A portrait of the man.",
				ImageType.PORTRAIT, LocalDate.of(2023, 3, 1), "Homer.png"));

		manager.addImage(new ImageRecord(6, "Red Kite", "A Red Kite bird of prey in flight.", ImageType.NATURE,
				LocalDate.of(2023, 4, 1), "RedKite.png"));

		manager.addImage(new ImageRecord(7, "Central Park", "An overhead view of Central Park New York USA.",
				ImageType.AERIAL, LocalDate.of(2023, 5, 1), "CentralPark.png"));

		manager.addImage(new ImageRecord(8, "Apples", "A bunch of apples.", ImageType.FOOD, LocalDate.of(2023, 6, 1),
				"Apples.png"));

		manager.addImage(new ImageRecord(9, "Programming Meme", "A Chat GPT programming meme.", ImageType.OTHER,
				LocalDate.of(2023, 7, 1), "ChatGPT.png"));

	}

	// Add Image
	private static void addImage(ImageManager manager, Scanner scanner) {

		System.out.print("Enter ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter title: ");
		String title = scanner.nextLine();

		System.out.print("Enter description: ");
		String description = scanner.nextLine();

		System.out.print("Enter genre: ");
		ImageType genre = ImageType.valueOf(scanner.nextLine().toUpperCase());

		System.out.print("Enter date (yyyy-mm-dd): ");
		LocalDate date = LocalDate.parse(scanner.nextLine());

		System.out.print("Enter thumbnail filename: ");
		String thumbnail = scanner.nextLine();

		ImageRecord image = new ImageRecord(id, title, description, genre, date, thumbnail);

		manager.addImage(image);

		System.out.println("Image added.");
	}

	// Search Menu
	private static void searchImages(ImageManager manager, Scanner scanner) {

		String options[] = { "Search by ID", "Search by Title", "Search by Description", "Search by Genre",
				"Search by Date Range" };

		Menu searchMenu = new Menu("Search Images", options);

		int choice = searchMenu.getUserChoice();

		switch (choice) {

		case 1:
			searchById(manager, scanner);
			break;

		case 2:
			searchByTitle(manager, scanner);
			break;

		case 3:
			searchByDescription(manager, scanner);
			break;

		case 4:
			searchByGenre(manager, scanner);
			break;

		case 5:
			searchByDate(manager, scanner);
			break;

		default:
			System.out.println("Inavlid option.");
		}
	}

	// Search Methods
	private static void searchById(ImageManager manager, Scanner scanner) {

		System.out.print("Enter ID: ");
		int id = scanner.nextInt();

		ImageRecord result = manager.searchId(id);

		if (result == null) {
			System.out.println("No image found.");
		} else {
			System.out.println(result);
		}
	}

	private static void searchByTitle(ImageManager manager, Scanner scanner) {

		System.out.println("Enter title keyword: ");
		String str = scanner.nextLine();

		displayAlbum(manager.searchTitle(str));
	}

	private static void searchByDescription(ImageManager manager, Scanner scanner) {

		System.out.println("Enter description keyword: ");
		String str = scanner.nextLine();

		displayAlbum(manager.searchDescription(str));
	}

	private static void searchByGenre(ImageManager manager, Scanner scanner) {

		System.out.println("Enter genre: ");
		ImageType type = ImageType.valueOf(scanner.nextLine().toUpperCase());

		displayAlbum(manager.searchGenre(type));
	}

	private static void searchByDate(ImageManager manager, Scanner scanner) {

		System.out.println("Enter start date (yyyy-mm-dd): ");
		LocalDate start = LocalDate.parse(scanner.nextLine());

		System.out.println("Enter end date (yyyy-mm-dd): ");
		LocalDate end = LocalDate.parse(scanner.nextLine());

		displayAlbum(manager.searchDates(start, end));
	}

	// Display Methods
	private static void displayAll(ImageManager manager) {
		displayAlbum(manager.getAllImages());
	}

	private static void displayAlbum(ImageAlbum album) {
		
		ImageRecord image = album.getFirst();

		if (image == null) {
			System.out.println("No images found.");
			return;
		}

		while (image != null) {
			System.out.println(image);
			image = album.getNext();
		}
	}
}
