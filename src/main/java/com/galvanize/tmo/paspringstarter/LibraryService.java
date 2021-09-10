package com.galvanize.tmo.paspringstarter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    
    // DB repository mock
    private Map<Long, Book> repository = Arrays.asList(
        new Book[]{
                
        }).stream()
        .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));
    
    // DB id sequence mock
    private AtomicLong sequence = new AtomicLong(0);
    
    public List<Book> readAll() {
        List<Book> bookList = repository.values().stream().collect(Collectors.toList());
        Collections.sort(bookList, new TitleComparator());
        return bookList;

       
    }
    
    public Book read(Long id) {
        return repository.get(id);
    }

    public Book create(Book book) {
        long key = sequence.incrementAndGet();
        book.setId(key);
        repository.put(key, book);
        return book;
    }
    
    public Book update(Long id, Book book) {
        book.setId(id);
        Book oldBook = repository.replace(id, book);
        return oldBook == null ? null : book;
    }
    
    public void deleteAll() {
        repository=Arrays.asList(
            new Book[]{
                    
            }).stream()
            .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));
            sequence = new AtomicLong(0);
    }
    public void delete(Long id) {
        repository.remove(id);
    }
}