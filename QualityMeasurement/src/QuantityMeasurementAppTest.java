package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    // =========================
    // SAME UNIT ADDITION
    // =========================
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET),
                LengthUnit.FEET
        );

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(6.0, LengthUnit.INCH),
                new QuantityLength(6.0, LengthUnit.INCH),
                LengthUnit.INCH
        );

        assertEquals(
                new QuantityLength(12.0, LengthUnit.INCH),
                result
        );
    }

    // =========================
    // CROSS UNIT ADDITION
    // =========================
    @Test
    public void testAddition_FeetPlusInches() {
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
    public void testAddition_InchesPlusFeet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.FEET),
                LengthUnit.INCH
        );

        assertEquals(
                new QuantityLength(24.0, LengthUnit.INCH),
                result
        );
    }

    @Test
    public void testAddition_YardPlusFeet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARD
        );

        assertEquals(
                new QuantityLength(2.0, LengthUnit.YARD),
                result
        );
    }

    // =========================
    // CM TEST
    // =========================
    @Test
    public void testAddition_CmPlusInch() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.54, LengthUnit.CENTIMETER),
                new QuantityLength(1.0, LengthUnit.INCH),
                LengthUnit.CENTIMETER
        );

        assertEquals(
                new QuantityLength(5.08, LengthUnit.CENTIMETER),
                result
        );
    }

    // =========================
    // COMMUTATIVITY
    // =========================
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
    public void testAddition_WithZero() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCH),
                LengthUnit.FEET
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
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.FEET
        );

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    public void testAddition_NullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        null,
                        new QuantityLength(1.0, LengthUnit.FEET),
                        LengthUnit.FEET
                )
        );
    }
}