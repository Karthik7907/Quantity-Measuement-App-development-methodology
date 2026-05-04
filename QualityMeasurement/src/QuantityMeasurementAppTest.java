package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    // =========================
    // SAME UNIT TESTS
    // =========================
    @Test
    void testFeetToFeet() {
        assertEquals(
                new QuantityLength(2.0, LengthUnit.FEET),
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(1.0, LengthUnit.FEET),
                        LengthUnit.FEET
                )
        );
    }

    @Test
    void testInchToInch() {
        assertEquals(
                new QuantityLength(12.0, LengthUnit.INCH),
                QuantityLength.add(
                        new QuantityLength(6.0, LengthUnit.INCH),
                        new QuantityLength(6.0, LengthUnit.INCH),
                        LengthUnit.INCH
                )
        );
    }

    // =========================
    // CROSS UNIT TESTS
    // =========================
    @Test
    void testFeetPlusInch_FeetTarget() {
        assertEquals(
                new QuantityLength(2.0, LengthUnit.FEET),
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCH),
                        LengthUnit.FEET
                )
        );
    }

    @Test
    void testFeetPlusInch_InchTarget() {
        assertEquals(
                new QuantityLength(24.0, LengthUnit.INCH),
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCH),
                        LengthUnit.INCH
                )
        );
    }

    // =========================
    // YARD TESTS
    // =========================
    @Test
    void testYardAddition() {
        assertEquals(
                new QuantityLength(2.0, LengthUnit.YARD),
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.YARD),
                        new QuantityLength(3.0, LengthUnit.FEET),
                        LengthUnit.YARD
                )
        );
    }

    // =========================
    // CENTIMETER TESTS
    // =========================
    @Test
    void testCentimeterAddition() {
        assertEquals(
                new QuantityLength(5.08, LengthUnit.CENTIMETER),
                QuantityLength.add(
                        new QuantityLength(2.54, LengthUnit.CENTIMETER),
                        new QuantityLength(1.0, LengthUnit.INCH),
                        LengthUnit.CENTIMETER
                )
        );
    }

    // =========================
    // COMMUTATIVITY
    // =========================
    @Test
    void testCommutativity() {

        QuantityLength r1 = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.YARD
        );

        QuantityLength r2 = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.FEET),
                LengthUnit.YARD
        );

        assertEquals(r1, r2);
    }

    // =========================
    // ZERO TEST
    // =========================
    @Test
    void testWithZero() {
        assertEquals(
                new QuantityLength(5.0, LengthUnit.FEET),
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(0.0, LengthUnit.INCH),
                        LengthUnit.FEET
                )
        );
    }

    // =========================
    // NEGATIVE TEST
    // =========================
    @Test
    void testNegativeAddition() {
        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(-2.0, LengthUnit.FEET),
                        LengthUnit.FEET
                )
        );
    }

    // =========================
    // NULL TESTS
    // =========================
    @Test
    void testNullSecondOperand() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        null,
                        LengthUnit.FEET
                )
        );
    }

    @Test
    void testNullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(1.0, LengthUnit.INCH),
                        null
                )
        );
    }

    // =========================
    // PRECISION TEST
    // =========================
    @Test
    void testPrecision() {
        assertEquals(
                new QuantityLength(1.6667, LengthUnit.YARD),
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(0.0, LengthUnit.INCH),
                        LengthUnit.YARD
                )
        );
    }
}