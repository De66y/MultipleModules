package nl.marktplaats.service;

import nl.marktplaats.data.BezorgwijzeDAO;
import nl.marktplaats.gedeeld.domeinmodel.Bezorgwijze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BezorgwijzeServiceTest {
    @Mock
    private BezorgwijzeDAO bezorgwijzeDAO;

    @InjectMocks
    private BezorgwijzeService bezorgwijzeService;

    private List<Bezorgwijze> bezorgwijzeTabel;

    @BeforeEach
    void setUp() {
        bezorgwijzeTabel = new ArrayList<>();
        bezorgwijzeTabel.add(new Bezorgwijze("Afhalen magazijn"));
        bezorgwijzeTabel.add(new Bezorgwijze("Thuis afhalen bij verkoper"));
        bezorgwijzeTabel.add(new Bezorgwijze("Versturen"));
        bezorgwijzeTabel.add(new Bezorgwijze("Versturen onder rembours"));
    }

    @Test
    void AantalBezorgwijzeOpvragenTest() {
        //Given

        //When
        Mockito.lenient().when(bezorgwijzeDAO.alleBezorgwijzen()).thenReturn(bezorgwijzeTabel);

        //Then
        assertEquals(4, bezorgwijzeTabel.size());
    }
}