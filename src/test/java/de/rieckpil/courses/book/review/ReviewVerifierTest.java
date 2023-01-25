package de.rieckpil.courses.book.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReviewVerifierTest {

  private ReviewVerifier review;

  @BeforeEach
  public void setUp() {
    review = new ReviewVerifier();

  }

  @Test
  @DisplayName("First Unit Test")
  void firstTest(){
    assertThat(5).isEqualTo(5);

  }

  @Test
  @DisplayName("Should Fail When Review Contains Swear Word")
  void swearWordTest(){
    String comment = "This product is shit";
    assertFalse(review.doesMeetQualityStandards(comment));
  }

  @Test
  @DisplayName("Should Fail When Review Contains Than Five (5) I's")
  void moreThanFiveIsTest() {
    String badComment2 = "I don't like and I wont buy and I wont gift and I wont recommend and I win, I, I I, I ,I , I I I";

    assertFalse(review.doesMeetQualityStandards(badComment2), "Comment contains more than five (5) I's");
  }

  @Test
  @DisplayName("Should Pass When Review Contains More Than Ten (10) Words")
  void moreThanTenWordsTest(){
    String goodComment2 = "I Realy love this product and it is also awesome for use";
    assertTrue(review.doesMeetQualityStandards(goodComment2), "Contains at least ten (10) words");

  }


  @DisplayName("Should Fail For Multiple Bad Reviews")
  @ParameterizedTest
  @CsvFileSource(resources ="/badReview.csv")
  void badReviewTests(String input) {

    boolean result = review.doesMeetQualityStandards(input);
    assertFalse(result);

  }

  @ParameterizedTest
  @ValueSource(strings = {"shit", "good good good good goof", "i I i i I I I"})
  @DisplayName("Should Fail When Using Disallowed Words Iin Reviews")
  void badReviewWordTest(String input)
  {
    assertFalse(review.doesMeetQualityStandards(input), "Using disallowed words in reviews");
  }


}
