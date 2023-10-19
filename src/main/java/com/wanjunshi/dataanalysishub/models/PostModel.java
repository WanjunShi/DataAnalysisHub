package com.wanjunshi.dataanalysishub.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A class representing the social media post
 */
public class PostModel implements Serializable {
	final private int id;
	final private String content;
	final private String author;
	final private int likes;
	final private int shares;
	final private LocalDateTime time;

	public PostModel(int id, String content, String author, int likes, int shares, LocalDateTime time) {
		super();
		this.id = id;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getAuthor() {
		return author;
	}

	public int getLikes() {
		return likes;
	}

	public int getShares() {
		return shares;
	}

	public LocalDateTime getTime() {
		return time;
	}

	
	@Override
	public String toString() {
		return id + "). content=" + content + ", author=" + author + ", likes=" + likes + ", shares="
				+ shares + ", time=" + time + "]";
	}


	/**
	 * Comparator that will be used to sort the post by likes
	 */
	public static class LikeComparator implements Comparator<PostModel>{

		@Override
		public int compare(PostModel o1, PostModel o2) {
			return Integer.compare(o1.likes, o2.likes);
		}
		
	}
	
	/**
	 * Comparator that will be used to sort the post by shares
	 */
	public static class ShareComparator implements Comparator<PostModel>{

		@Override
		public int compare(PostModel o1, PostModel o2) {
			return Integer.compare(o1.shares, o2.shares);
		}
		
	}
}
