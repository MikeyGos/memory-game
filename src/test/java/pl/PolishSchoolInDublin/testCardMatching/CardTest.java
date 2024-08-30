package pl.PolishSchoolInDublin.testCardMatching;

import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.PolishSchoolInDublin.Card;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("hiena");
    }

    @Test
    void testCardCreation() {
        assertNotNull(card, "Card should be created");
        assertEquals("hiena", card.getCardName(), "Card name should be 'hiena'");
    }

    @Test
    void testSetCardNameValid() {
        card.setCardName("lew");
        assertEquals("lew", card.getCardName(), "Card name should be set to 'lew'");
    }

    @Test
    void testSetCardNameInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            card.setCardName("invalidCard");
        });
        assertEquals("invalidcard nieprawidłowa karta! Dostępne karty: [hiena, lew, mufasa, pumba, rafiki, skaza, timon, zazu]", exception.getMessage());
    }

    @Test
    void testPathName() {
        assertEquals("imagesCard/hiena.jpg", card.pathName(), "Path name should be 'imagesCard/hiena.jpg'");
    }

    @Test
    void testGetImage() {
        assertDoesNotThrow(() -> {
            card.getImage();
        }, "Image should be loaded without exceptions");
    }

    @Test
    void testGetQuestionMark() {
        assertDoesNotThrow(() -> {
            card.getQuestionMark();
        }, "Question mark image should be loaded without exceptions");
    }

    @Test
    void testGetRandomCard() {
        assertDoesNotThrow(() -> {
            Image image = card.getRandomCard();
            assertNotNull(image, "Random card image should not be null");
        });
    }

    @Test
    void testEqualsAndHashCode() {
        Card card1 = new Card("hiena");
        Card card2 = new Card("hiena");
        Card card3 = new Card("lew");

        assertEquals(card1, card2, "Cards with the same name should be equal");
        assertNotEquals(card1, card3, "Cards with different names should not be equal");
        assertEquals(card1.hashCode(), card2.hashCode(), "Hash codes should be equal for cards with the same name");
    }
}