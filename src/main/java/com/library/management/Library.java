package com.library.management;

import java.util.ArrayList;
import java.util.List;

public class Library {


        private String name;
        private int maxBooksPerMember;
        private List<Item> items;
        private List<Member> members;

        public Library(String name, int maxBooksPerMember) {
            this.name = name;
            this.maxBooksPerMember = maxBooksPerMember;
            this.items = new ArrayList<>();
            this.members = new ArrayList<>();
        }

        // Add item
        public void addItem(Item item) {
            items.add(item);
            System.out.println(item.getTitle() + " has been added to the library.");
        }

        // Register member
        public void registerMember(Member member) {
            members.add(member);
            System.out.println(member.getName() + " has been registered as a member.");
        }

        public void displayStatus() {
            System.out.println("\nLibrary: " + name);
            System.out.println("Items in Library:");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println("Members Registered:");
            for (Member member : members) {
                System.out.println(member.getName());
            }
        }

}
