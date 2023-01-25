package de.rieckpil.courses.book.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookSynchronizationListenerTest {

  @Mock
  private BookRepository bookRepository;

  @Mock
  private OpenLibraryApiClient openLibraryApiClient;

  @InjectMocks
  private BookSynchronizationListener bookSynchronizationListenerCut;

  private BookSynchronization invalidIsbn;
  private BookSynchronization validIsbn;

  private String correctISBN;

  @BeforeEach
  void setUp() {
    correctISBN = "1234567812123";
    invalidIsbn = new BookSynchronization("hjhdf");
    validIsbn = new BookSynchronization(correctISBN);

  }


  @Test
  @DisplayName("Should Fail If isbn is Malformed")
  void testMalformedIsbn() {
    bookSynchronizationListenerCut.consumeBookUpdates(invalidIsbn);
    Mockito.verifyNoInteractions(bookRepository, openLibraryApiClient);


  }

  @Test
  @DisplayName("Should Pass If isbn length is more than 13")
  void testIsbnlength(){
    Mockito.when(bookRepository.findByIsbn(correctISBN)).thenReturn(new Book());
    bookSynchronizationListenerCut.consumeBookUpdates(validIsbn);
    Mockito.verify(bookRepository, Mockito.times(0)).save(ArgumentMatchers.any());


  }
}
