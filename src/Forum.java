import javafx.geometry.Pos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Forum {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Post> posts = readFile();

        Scanner consoleScanner = new Scanner(System.in);

        int replyId = -1;
        while (true) {
            // loop over posts and print the ones with the right reply id
            printPosts(posts, replyId);
            // ask the user to type a new reply id
            System.out.println("Type the id you want to see replies to:");
            replyId = Integer.valueOf(consoleScanner.nextLine());
        }
    }

    public static ArrayList<Post> readFile() throws FileNotFoundException {
        ArrayList<Post> posts = new ArrayList<>();
        File f = new File("posts.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Post post = new Post(Integer.valueOf(columns[0]), columns[1], columns[2]);
            posts.add(post);
        }
        return posts;
    }

    public static void printPosts(ArrayList<Post> posts, int replyId) {
        int id = 0;
        for (Post post : posts) {
            if (post.replyId == replyId) {
                System.out.printf("(%d) %s by %s\n", id, post.text, post.author);
            }
            id++;
        }
    }
}