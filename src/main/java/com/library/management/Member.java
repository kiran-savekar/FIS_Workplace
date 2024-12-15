package com.library.management;

import java.util.ArrayList;
import java.util.List;

public class Member {

        private String name;
        private List<Item> borrowedItems;

        public Member(String name) {
            this.name = name;
            this.borrowedItems = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void borrowItem(Item item) {
            borrowedItems.add(item);
            System.out.println(name + " borrowed: " + item.getTitle());
        }

        public void returnItem(Item item) {
            borrowedItems.remove(item);
            System.out.println(name + " returned: " + item.getTitle());
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Member " + name + " is being garbage collected.");
        }

}
