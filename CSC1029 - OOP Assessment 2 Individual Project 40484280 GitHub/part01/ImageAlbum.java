package part01;

import java.util.ArrayList;

public class ImageAlbum {
	
	private ArrayList<ImageRecord> images;
	private int index;
	
	public ImageAlbum() {
		images = new ArrayList<>();
		index = -1;
	}
	
	//Add images in chronological order from oldest to newest
	public void addImage(ImageRecord image) {

	    ArrayList<ImageRecord> newList = new ArrayList<>();

	    boolean added = false;

	    for (int i = 0; i < images.size(); i++) {

	        if (!added && image.getDateTaken().isBefore(images.get(i).getDateTaken())) {
	            newList.add(image);
	            added = true;
	        }

	        newList.add(images.get(i));
	    }

	    // If not added yet, add at end
	    if (!added) {
	        newList.add(image);
	    }

	    // Replace old list
	    images = newList;
	}
	
	public ImageRecord getFirst() {
		if (images.size() == 0) {
			return null;
		}
		
		index = 0;
		return images.get(index);
	}
	
	public ImageRecord getNext() {
		if (images.size() == 0 || index == -1) {
			return null;
		}
		
		if(index + 1 >= images.size()) {
			return null;
		}
		
		index++;
		return images.get(index);
	}
	
	public ImageRecord getPrevious() {
		if (images.size() == 0 || index <=0) {
			return null;
		}
		
		index--;
		return images.get(index);
	}
	
	

}
