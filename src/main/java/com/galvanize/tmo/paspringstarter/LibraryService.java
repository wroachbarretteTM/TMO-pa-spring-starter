package com.galvanize.tmo.paspringstarter;
import java.util.Arrays;
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
                new Book("Title", "Author", "Year Published", 1),
                new Book("Title1", "Author1", "Year Published1", 2),
                new Book("Title2", "Author2", "Year Published2", 3),
        }).stream()
        .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));
    
    // DB id sequence mock
    private AtomicLong sequence = new AtomicLong(3);
    
    public List<Book> readAll() {
        return repository.values().stream().collect(Collectors.toList());
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
    
    public void delete() {
        repository=Arrays.asList(
            new Book[]{
                    
            }).stream()
            .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));
    }
}