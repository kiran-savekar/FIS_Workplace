package com.library.management;

public class LibraryApp {


        public static void main(String[] args) {
            // Accept command line arguments
            if (args.length < 2) {
                System.out.println("Usage: java LibraryApp <Library Name> <Max Books Per Member>");
                return;
            }

            String libraryName = args[0];
            int maxBooksPerMember = Integer.parseInt(args[1]);

            // Initialize Library
            Library library = new Library(libraryName, maxBooksPerMember);

            // Add Items
            library.addItem(new Book(1, "Java Programming", "James Gosling"));
            library.addItem(new Magazine(2, "Tech Monthly", 45));

            // Register Members
            Member member1 = new Member("Alice");
            Member member2 = new Member("Bob");
            library.registerMember(member1);
            library.registerMember(member2);

            // Borrow and Return Items
            member1.borrowItem(new Book(1, "Java Programming", "James Gosling"));
            member1.returnItem(new Book(1, "Java Programming", "James Gosling"));

            // Display Library Status
            library.displayStatus();
        }

}
