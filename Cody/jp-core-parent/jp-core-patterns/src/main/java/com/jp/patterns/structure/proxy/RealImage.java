/**
 * 
 */
package com.jp.patterns.structure.proxy;

/**
 * @author dimit.chadha
 * 
 */
public class RealImage implements Image {

	private String filename;

	public RealImage(String filename) {
		this.filename = filename;
		loadImageFromDisk();
	}

	private void loadImageFromDisk() {
		System.out.println("Loading Image From Disk  :: " + filename);

	}

	
	public void displayImage() {
		System.out.println("Displaying :: " + filename);

	}

}
