package part01;

import java.time.LocalDate;
import java.util.ArrayList;

public class ImageManager {

	private ArrayList<ImageRecord> images;

	public ImageManager() {
		images = new ArrayList<>();
	}

	public void addImage(ImageRecord image) {
		images.add(image);
	}

	public ImageRecord searchId(int id) {
		for (int i = 0; i < images.size(); i++) {
			if (images.get(i).getId() == id) {
				return images.get(i);
			}
		}
		return null;

	}

	public ImageAlbum searchTitle(String str) {
		ImageAlbum album = new ImageAlbum();

		for (int i = 0; i < images.size(); i++) {
			if (images.get(i).getTitle().contains(str)) {
				album.addImage(images.get(i));
			}
		}
		return album;

	}
	
	public ImageAlbum searchDescription(String str) {
		ImageAlbum album = new ImageAlbum();

		for (int i = 0; i < images.size(); i++) {
			if (images.get(i).getDescription().contains(str)) {
				album.addImage(images.get(i));
			}
		}
		return album;

	}

	public ImageAlbum searchGenre(ImageType type) {
		ImageAlbum album = new ImageAlbum();

		for (int i = 0; i < images.size(); i++) {
			if (images.get(i).getGenre() == type) {
				album.addImage(images.get(i));
			}
		}
		return album;

	}

	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		ImageAlbum album = new ImageAlbum();

		for (int i = 0; i < images.size(); i++) {
			LocalDate date = images.get(i).getDateTaken();
			if ((date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end))) {
				album.addImage(images.get(i));
			}
		}
		return album;
		
	}
	
	public ImageAlbum getAllImages() {
		ImageAlbum album = new ImageAlbum();

		for (int i = 0; i < images.size(); i++) {
			album.addImage(images.get(i));
		}
		return album;
		
	}
	
	
	

}
