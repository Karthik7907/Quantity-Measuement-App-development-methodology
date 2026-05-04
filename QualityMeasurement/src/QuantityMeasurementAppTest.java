package com.apps.quantitymeasurement;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ---------- EQUALITY ----------
    @Test
    public void testEquality_KilogramToGram_Equivalent() {
        assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }



    @Test
    public void testEquality_Negative() {
        assertTrue(new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_Zero() {
        assertTrue(new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
    }

    @Test
    public void testEquality_DifferentCategory() {
        assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals("1 foot"));
    }

    // ---------- CONVERSION ----------
    @Test
    public void testConvert_KgToGram() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConvert_GramToKg() {
        QuantityWeight result =
                new QuantityWeight(1000.0, WeightUnit.GRAM)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConvert_PoundToKg() {
        QuantityWeight result =
                new QuantityWeight(2.20462, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    // ---------- ADDITION ----------
    @Test
    public void testAddition_SameUnit() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM)
        );

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_WithTargetUnit() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );

        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_Negative() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM),
                new QuantityWeight(-2000.0, WeightUnit.GRAM)
        );

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_WithZero() {
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM),
                new QuantityWeight(0.0, WeightUnit.GRAM)
        );

        assertEquals(5.0, result.getValue(), EPSILON);
    }


}
