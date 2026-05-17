package ru.itmo.stub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.itmo.stub.LogStubFactory.X1;
import static ru.itmo.stub.LogStubFactory.X2;
import static ru.itmo.stub.LogStubFactory.X3;

class LogStubFactoryTest {

    private static final double EPS = 1e-10;

    @Test
    void shouldReturnLnValuesFromTable() {
        assertEquals(0.6931471806, LogStubFactory.lnStub().apply(X1), EPS);
        assertEquals(1.6094379124, LogStubFactory.lnStub().apply(X2), EPS);
        assertEquals(3.2188758249, LogStubFactory.lnStub().apply(X3), EPS);
    }

    @Test
    void shouldReturnLog2ValuesFromTable() {
        assertEquals(1.0, LogStubFactory.log2Stub().apply(X1), EPS);
        assertEquals(2.3219280949, LogStubFactory.log2Stub().apply(X2), EPS);
        assertEquals(4.6438561898, LogStubFactory.log2Stub().apply(X3), EPS);
    }

    @Test
    void shouldReturnLog5ValuesFromTable() {
        assertEquals(0.4306765581, LogStubFactory.log5Stub().apply(X1), EPS);
        assertEquals(1.0, LogStubFactory.log5Stub().apply(X2), EPS);
        assertEquals(2.0, LogStubFactory.log5Stub().apply(X3), EPS);
    }

    @Test
    void shouldContainBaseValuesForBaseLogFunction() {
        assertEquals(0.6931471806, LogStubFactory.lnStub().apply(2.0), EPS);
        assertEquals(1.6094379124, LogStubFactory.lnStub().apply(5.0), EPS);
    }
}