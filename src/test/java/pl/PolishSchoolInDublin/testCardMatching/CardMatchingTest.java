package pl.PolishSchoolInDublin.testCardMatching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.PolishSchoolInDublin.matching.CardMatching;

import static org.junit.jupiter.api.Assertions.*;

class CardMatchingTest {

    private CardMatching card1;
    private CardMatching card2;
    private CardMatching card3;

    @BeforeEach
    void setUp() {
        card1 = new CardMatching("hiena");
        card2 = new CardMatching("lew");
        card3 = new CardMatching("hiena");
    }

    @Test
    void testCardCreation() {
        assertNotNull(card1, "Card should be created");
        assertEquals("hiena", card1.getCardName(), "Card name should be 'hiena'");
    }

    @Test
    void testIsMatchedInitially() {
        assertFalse(card1.isMatched(), "New card should not be matched initially");
    }

    @Test
    void testSetMatched() {
        card1.setMatched(true);
        assertTrue(card1.isMatched(), "Card should be matched after setting matched to true");
    }

    @Test
    void testSameCardMatching() {
        assertTrue(card1.sameCard(card3), "Cards with the same name should match");
        assertFalse(card1.sameCard(card2), "Cards with different names should not match");
    }

    @Test
    void testCardNameInheritance() {
        assertEquals("lew", card2.getCardName(), "Card name should be inherited from Card class");
    }
}