package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class PostList {
	ArrayList<Post> postList = new ArrayList<Post>();
	String file;

	PostList(String file) {
		this.file = file;
	}

	public void addPostToFile(Post newPost) {
		try {
		BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
		
		out.write(newPost.toString());
		System.out.println(newPost.toString());
		out.close();
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public String toString() {
		String s = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				s += line + "\n";
			}
			reader.close();
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Post> getPostList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] postLine = line.split("\\|");
				Post newPost = new Post(postLine[0], postLine[1], postLine[2]);
				postList.add(newPost);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.reverse(postList);
		return postList;
	}

}
