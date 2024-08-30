//package pl.PolishSchoolInDublin.testController;
//
//import javafx.scene.control.Label;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import pl.PolishSchoolInDublin.controllers.PlayerVsComputerController;
//import pl.PolishSchoolInDublin.matching.CardMatching;
//
//import java.util.ArrayList;
//import java.util.Random;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@Nested
//class PlayerVsComputerControllerTest {
//
//    private PlayerVsComputerController controller;
//
//    @Mock
//    private Label firstPlayerScore;
//    @BeforeEach
//    void setUp() {
//        controller = new PlayerVsComputerController();
//        controller.random = mock(Random.class);
//        controller.usedCardDeck = new ArrayList<>();
//        // Ensure you use valid card names here
//        for (int i = 0; i < 10; i++) {
//            controller.usedCardDeck.add(new CardMatching("hiena")); // Change to valid names if needed
//        }
//    }
//    @Test
//    void testGetRandomUnmatchedCardIndex() {
//        when(controller.random.nextInt(anyInt())).thenReturn(0, 1, 2); // Mocking random behavior
//        controller.usedCardDeck.get(2).setMatched(true); // Set some cards as matched
//
//        int index = controller.getRandomUnmatchedCardIndex();
//        assertFalse(controller.usedCardDeck.get(index).isMatched(), "The selected card should not be matched");
//    }
//
//
//}