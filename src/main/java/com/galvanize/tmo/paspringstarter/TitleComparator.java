package com.galvanize.tmo.paspringstarter;
import java.util.Comparator;

public class TitleComparator implements Comparator<Book> {


   
        public int compare(final Book object1, final Book object2) {
            return object1.getTitle().compareTo(object2.getTitle());
        }
    

  }
  