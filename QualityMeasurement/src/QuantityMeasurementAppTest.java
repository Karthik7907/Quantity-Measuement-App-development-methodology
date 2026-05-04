package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.LengthUnit;

public class QuantityMeasurementAppTest {

    // =========================
    // UC1 + UC2 + UC3 + UC4 + UC8: EQUALITY TESTS
    // =========================

    @Test
    public void testFeetEquality_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    public void testInchEquality_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.INCH)));
    }

    @Test
    public void testCrossUnit_FeetToInch() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(12.0, LengthUnit.INCH)));
    }

    @Test
    public void testCrossUnit_YardToFeet() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(3.0, LengthUnit.FEET)));
    }

    @Test
    public void testCrossUnit_CentimeterToInch() {
        assertTrue(new QuantityLength(2.54, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(1.0, LengthUnit.INCH)));
    }

    // =========================
    // UC5: CONVERSION TESTS
    // =========================

    @Test
    public void testConvert_FeetToInch() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), 0.0001);
    }

    @Test
    public void testConvert_YardToFeet() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.YARD).convertTo(LengthUnit.FEET);

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    public void testConvert_InchToFeet() {
        QuantityLength result =
                new QuantityLength(12.0, LengthUnit.INCH).convertTo(LengthUnit.FEET);

        assertEquals(1.0, result.getValue(), 0.0001);
    }

    // =========================
    // UC6: ADDITION (DEFAULT UNIT = FIRST OPERAND)
    // =========================

    @Test
    public void testAddition_FeetPlusFeet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET)
        );

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testAddition_FeetPlusInch() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH)
        );

        assertEquals(
                new QuantityLength(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testAddition_WithZero() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCH)
        );

        assertEquals(
                new QuantityLength(5.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testAddition_NegativeValues() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET)
        );

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    // =========================
    // UC7: ADDITION WITH TARGET UNIT
    // =========================



    @Test
    public void testAddition_ExplicitTargetUnit_Inch() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.INCH
        );

        assertEquals(
                new QuantityLength(24.0, LengthUnit.INCH),
                result
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.FEET
        );

        assertEquals(
                new QuantityLength(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityLength r1 = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.FEET
        );

        QuantityLength r2 = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.FEET),
                LengthUnit.FEET
        );

        assertEquals(r1, r2);
    }

    // =========================
    // EDGE CASES
    // =========================

    @Test
    public void testInvalidValue_NaN() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    public void testNullAddition() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        null
                ));
    }
}