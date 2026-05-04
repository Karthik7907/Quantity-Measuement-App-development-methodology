package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;
import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH),
                EPS);
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityLength.convert(24.0, LengthUnit.INCH, LengthUnit.FEET),
                EPS);
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityLength.convert(1.0, LengthUnit.YARD, LengthUnit.INCH),
                EPS);
    }

    @Test
    public void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityLength.convert(72.0, LengthUnit.INCH, LengthUnit.YARD),
                EPS);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH),
                1e-3); // relaxed epsilon
    }

    @Test
    public void testConversion_FeetToYards() {
        assertEquals(2.0,
                QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARD),
                EPS);
    }

    @Test
    public void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCH),
                EPS);
    }

    @Test
    public void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH),
                EPS);
    }

    @Test
    public void testConversion_RoundTrip() {
        double v = 5.5;
        double result = QuantityLength.convert(
                QuantityLength.convert(v, LengthUnit.FEET, LengthUnit.INCH),
                LengthUnit.INCH,
                LengthUnit.FEET
        );

        assertEquals(v, result, EPS);
    }

    @Test
    public void testConversion_SameUnit() {
        assertEquals(5.0,
                QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                EPS);
    }

    @Test
    public void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    public void testConversion_NaNOrInfinite() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));

        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }
}