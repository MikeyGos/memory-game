package pl.PolishSchoolInDublin.testController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.PolishSchoolInDublin.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = new Card("hiena");
    }

    @Test
    void testCardCreation() {
        assertEquals("hiena", card.getCardName());
    }

    @Test
    void testInvalidCardName() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Card("invalidCardName"); // Użyj niepoprawnej nazwy
        });
        assertEquals("invalidcardname nieprawidłowa karta! Dostępne karty: [hiena, lew, mufasa, pumba, rafiki, skaza, timon, zazu]", thrown.getMessage());
    }
}