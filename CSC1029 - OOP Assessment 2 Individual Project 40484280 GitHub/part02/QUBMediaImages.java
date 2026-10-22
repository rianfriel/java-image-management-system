package part02;

import part01.ImageAlbum;
import part01.ImageManager;
import part01.ImageRecord;
import part01.ImageType;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.ImageIcon;

import console.Console;




public class QUBMediaImages {

	public static void main(String[] args) {
		Console console = createConsole();
		
		ImageManager manager = new ImageManager();
		loadDefaultImages(manager);
		
		boolean running = true;
		
		while (running) {
			showMainMenu(console);
			
			String input = console.readLn();
			if (input == null || input.isEmpty()) {
			    console.println("Invalid input. Try again.");
			    continue;
			}

			int choice = Integer.parseInt(input);
			
			switch (choice) {
				case 1:
					addImage(console, manager);
					break;	
				case 2:
					searchImages(console, manager);
					break;
				case 3:
					displayAll(console, manager);
					break;
				case 4:
					running = false;
					break;
					
				default:
					console.println("Invalid option.");
			}
			pause(console);
		}
		
	}
	
	public static Console createConsole() {
		Console con = new Console(true);
		con.setSize(1240, 440);
		con.setVisible(true);
		con.setFont(new Font("Courier", Font.BOLD, 20));
		return con;
		}

	
	//UI Methods
	private static void showMainMenu(Console console) {
		
		console.clear();
		
		console.setBgColour(Color.BLACK);
		console.setColour(Color.CYAN);
		console.setFont(new Font("Arial", Font.BOLD, 20));
		
		console.println("===============================");
		console.println("	QUB MEDIA IMAGES"	);
		console.println("===============================");

		console.setColour(Color.WHITE);
		console.setFont(new Font("Arial", Font.PLAIN, 16));
		
		console.println("1. Add Image");
		console.println("2. Search");
		console.println("3. Display All");
		console.println("4. Exit");
		console.println("");
		console.println("Enter Selection");
	}
	
	private static void pause(Console console) {
		console.println("\nPress enter to continue...");
		console.readLn();
	}
	
    // Load Default Data
    private static void loadDefaultImages(ImageManager manager) {
        manager.addImage(new ImageRecord(1, "Andromeda Galaxy",
                "Image of the Andromeda galaxy.",
                ImageType.ASTRONOMY,
                LocalDate.of(2023, 1, 1),
                "Andromeda.png"));

        manager.addImage(new ImageRecord(2, "Lanyon QUB",
                "An image of the QUB Lanyon building.",
                ImageType.ARCHITECTURE,
                LocalDate.of(2023, 2, 1),
                "LanyonQUB.png"));

        manager.addImage(new ImageRecord(3, "Kermit Plays Golf",
                "An image of Kermit the frog playing golf.",
                ImageType.SPORT,
                LocalDate.of(2023, 3, 1),
                "KermitGolf.png"));

        manager.addImage(new ImageRecord(4, "Mourne Mountains",
        		"A panoramic view of the Mourne mountains.",
        		ImageType.LANDSCAPE,
        		LocalDate.of(2023, 4, 1),
        		"Mournes.png"));

        manager.addImage(new ImageRecord(5, "Homer Simpson",
        		"Homer Simpson- A portrait of the man.",
        		ImageType.PORTRAIT,
        		LocalDate.of(2023, 3, 1),
        		"Homer.png"));

        manager.addImage(new ImageRecord(6, "Red Kite",
            	"A Red Kite bird of prey in flight.",
            	ImageType.NATURE,
            	LocalDate.of(2023, 4, 1),
            	"RedKite.png"));

        manager.addImage(new ImageRecord(7, "Central Park",
            	"An overhead view of Central Park New York USA.",
            	ImageType.AERIAL,
            	LocalDate.of(2023, 5, 1),
            	"CentralPark.png"));

        manager.addImage(new ImageRecord(8, "Apples",
            	"A bunch of apples.",
            	ImageType.FOOD,
            	LocalDate.of(2023, 6, 1),
            	"Apples.png"));

        manager.addImage(new ImageRecord(9, "Programming Meme",
            	"A Chat GPT programming meme.",
            	ImageType.OTHER,
            	LocalDate.of(2023, 7, 1),
            	"ChatGPT.png"));
    }

    // Add Image
    private static void addImage(Console console, ImageManager manager) {

        console.clear();
        console.println("ADD IMAGE");

        console.println("Enter ID:");
        int id = Integer.parseInt(console.readLn());

        console.println("Enter title:");
        String title = console.readLn();

        console.println("Enter description:");
        String description = console.readLn();

        console.println("Enter genre:");
        ImageType genre = ImageType.valueOf(console.readLn().toUpperCase());

        console.println("Enter date (yyyy-mm-dd):");
        LocalDate date = LocalDate.parse(console.readLn());

        console.println("Enter thumbnail filename:");
        String thumbnail = console.readLn();

        ImageRecord image = new ImageRecord(id, title, description, genre, date, thumbnail);

        manager.addImage(image);

        console.setColour(Color.GREEN);
        console.println("Image added successfully!");
        console.setColour(Color.WHITE);
    }

    // Search Menu
    private static void searchImages(Console console, ImageManager manager) {

        console.clear();

        console.setColour(Color.YELLOW);
        console.println("SEARCH MENU");
        console.setColour(Color.WHITE);

        console.println("1. Search by ID");
        console.println("2. Search by Title");
        console.println("3. Search by Description");
        console.println("4. Search by Genre");
        console.println("5. Search by Date Range");

        int choice = Integer.parseInt(console.readLn());

        switch (choice) {
            case 1:
                console.println("Enter ID:");
                int id = Integer.parseInt(console.readLn());
                ImageRecord result = manager.searchId(id);
                displaySingle(console, result);
                break;
                
            case 2:
                console.println("Enter title keyword:");
                displayAlbum(console, manager.searchTitle(console.readLn()));
                break;
            case 3:
                console.println("Enter description keyword:");
                displayAlbum(console, manager.searchDescription(console.readLn()));
                break;
                
            case 4:
                console.println("Enter genre:");
                ImageType type = ImageType.valueOf(console.readLn().toUpperCase());
                displayAlbum(console, manager.searchGenre(type));
                break;
                
            case 5:
                console.println("Enter start date:");
                LocalDate start = LocalDate.parse(console.readLn());

                console.println("Enter end date:");
                LocalDate end = LocalDate.parse(console.readLn());

                displayAlbum(console, manager.searchDates(start, end));
                break;
        }
    }

    // Display Methods
    private static void displayAll(Console console, ImageManager manager) {
        displayAlbum(console, manager.getAllImages());
    }

    private static void displaySingle(Console console, ImageRecord image) {

        if (image == null) {
            console.println("No image found.");
            return;
        }

        console.println(image);
        console.println(new ImageIcon("Images/" + image.getThumbnail()));
    }

    private static void displayAlbum(Console console, ImageAlbum album) {

        ImageRecord image = album.getFirst();

        if (image == null) {
            console.println("No images found.");
            return;
        }

        while (image != null) {

            console.println(image);
            console.println(new ImageIcon("Images/" + image.getThumbnail()));
            console.println("--------------------------------");

            image = album.getNext();
        }
    }
    
    public static ImageIcon getImage(int value) {
    	if ( value>=0 && value<=8 ) {
    		switch(value) {
    		case 0:return new ImageIcon("Images\\Andromeda.png");
    		case 1:return new ImageIcon("Images\\LanyonQUB.png");
    		case 2:return new ImageIcon("Images\\KermitGolf.png");
    		case 3:return new ImageIcon("Images\\Mournes.png");
    		case 4:return new ImageIcon("Images\\Homer.png");
    		case 5:return new ImageIcon("Images\\RedKite.png");
    		case 6:return new ImageIcon("Images\\CentralPark.png");
    		case 7:return new ImageIcon("Apples.png");
    		case 8:return new ImageIcon("ChatGPT.png");
    		}
    	}
    	return null;
    }
}
	
	
	
	
	

