package ru.itmo.stub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.itmo.stub.TrigStubFactory.X1;
import static ru.itmo.stub.TrigStubFactory.X2;
import static ru.itmo.stub.TrigStubFactory.X3;

class TrigStubFactoryTest {

    private static final double EPS = 1e-10;

    @Test
    void shouldReturnCosValuesFromTable() {
        assertEquals(0.5403023059, TrigStubFactory.cosStub().apply(X1), EPS);
        assertEquals(-0.4161468365, TrigStubFactory.cosStub().apply(X2), EPS);
        assertEquals(-0.2107957994, TrigStubFactory.cosStub().apply(X3), EPS);
    }

    @Test
    void shouldReturnSinValuesFromTable() {
        assertEquals(-0.8414709848, TrigStubFactory.sinStub().apply(X1), EPS);
        assertEquals(-0.9092974268, TrigStubFactory.sinStub().apply(X2), EPS);
        assertEquals(0.9775301177, TrigStubFactory.sinStub().apply(X3), EPS);
    }

    @Test
    void shouldReturnTanValuesFromTable() {
        assertEquals(-1.5574077247, TrigStubFactory.tanStub().apply(X1), EPS);
        assertEquals(2.1850398633, TrigStubFactory.tanStub().apply(X2), EPS);
        assertEquals(-4.6373320546, TrigStubFactory.tanStub().apply(X3), EPS);
    }

    @Test
    void shouldReturnCotValuesFromTable() {
        assertEquals(-0.6420926159, TrigStubFactory.cotStub().apply(X1), EPS);
        assertEquals(0.4576575544, TrigStubFactory.cotStub().apply(X2), EPS);
        assertEquals(-0.2156415029, TrigStubFactory.cotStub().apply(X3), EPS);
    }

    @Test
    void shouldReturnSecValuesFromTable() {
        assertEquals(1.8508157177, TrigStubFactory.secStub().apply(X1), EPS);
        assertEquals(-2.4029979617, TrigStubFactory.secStub().apply(X2), EPS);
        assertEquals(-4.7439275484, TrigStubFactory.secStub().apply(X3), EPS);
    }

    @Test
    void shouldReturnCscValuesFromTable() {
        assertEquals(-1.1883951058, TrigStubFactory.cscStub().apply(X1), EPS);
        assertEquals(-1.0997501703, TrigStubFactory.cscStub().apply(X2), EPS);
        assertEquals(1.0229879368, TrigStubFactory.cscStub().apply(X3), EPS);
    }
}