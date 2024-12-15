package com.library.management;

import java.util.Date;

public class Transaction {

        private Member member;
        private Item item;
        private Date dateBorrowed;
        private Date dateReturned;

        public Transaction(Member member, Item item, Date dateBorrowed) {
            this.member = member;
            this.item = item;
            this.dateBorrowed = dateBorrowed;
        }

        public void recordReturn(Date dateReturned) {
            this.dateReturned = dateReturned;
        }

        @Override
        public String toString() {
            return "Transaction [Member: " + member.getName() + ", Item: " + item.getTitle()
                    + ", Borrowed: " + dateBorrowed
                    + (dateReturned != null ? ", Returned: " + dateReturned : ", Not yet returned") + "]";
        }

}
