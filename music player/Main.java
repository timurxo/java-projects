package com.example;

import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Album album = new Album("Silver Side Up");

        album.addSong(new Song("Never Again", 2.3));
        album.addSong(new Song("Too Bad", 5.01));
        album.addSong(new Song("Where Do I Hide", 5.01));
        album.addSong(new Song("Good Times Go", 4.01));

        showOptionsToUser();
        userInterface(album);
        


    }

    private static void userInterface(Album album) {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        // flag to decide when to quit the application
        boolean quit = false;

        // ------- for choice 1 ---------
        int x = rand.nextInt(album.getSongsInAlbum().size());
        String name1 = album.getSongsInAlbum().get(x).getSongName();
        // ------------------------------

        // ---------------- For choices 3 and 4 --------------
        ListIterator<Song> iterator = album.getSongsInAlbum().listIterator();
        boolean nextOne = true;

        while (!quit) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    album.showIt();
                    break;
                case 1:
                    System.out.println(name1);
                    break;
                case 2: // ---------------- next song ------------------------------
                    // set the direction
                    if (!nextOne) { // move forward if not
                        if (iterator.hasNext()) {    // check the next one
                            iterator.next();    // get the next one
                        }
                        nextOne = true;
                    }

                    if (iterator.hasNext()) {
                        System.out.println("Now listening to " + iterator.next().getSongName());
                    } else {
                        System.out.println("You have reached the end of the album ");
                        nextOne = false; // can't go further
                    }

                    break;
                case 3: // ---------------- previous song ----------------------------
                    if (nextOne) {
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                        nextOne = false;
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println("Now listening to " + iterator.previous().getSongName());
                    } else {
                        System.out.println("You have reached the beginning of the album");
                        nextOne = true;
                    }

                    break;
                case 4: // ---------------------- add song ----------------------------------
                    System.out.println("Enter name of the song: ");
                    Scanner sc1 = new Scanner(System.in);
                    String name = sc1.nextLine();
                    System.out.println("Enter duration of the song: ");
                    Scanner sc2 = new Scanner(System.in);
                    double duration = sc2.nextDouble();

                    album.addSong(new Song(name, duration));

                    System.out.println("--- Updated album list --- ");
                    album.showIt();

                    break;
                case 5: // ---------------------- delete song ----------------------------------
                    try {
                        if (album.getSongsInAlbum().size() > 0) {
                            iterator.remove();
                            System.out.println("--- Updated album list --- ");
                            album.showIt();
                        }
                    } catch (IllegalStateException e) {
                        System.out.println("You need to pick the song first!");
                    }
                    break;
                case 6:
                    showOptionsToUser();
                    break;
                case 7:
                    System.out.println("You quit the player");
                    quit = true;
                    break;
            }
        }

    }

    private static void showOptionsToUser() {
        System.out.println();
        System.out.println("To show songs in album choose: 0" + "\n" +
                "To play random song choose: 1" + "\n" +
                "To play next song choose:  2" + "\n" +
                "To play previous song choose:  3" + "\n" +
                "To add song to the album choose:  4" + "\n" +
                "To remove song from the album choose:  5" + "\n" +
                "To show options again choose:  6" + "\n" +
                "To quit choose : 7" + "\n");
    }

}
