package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.QuantityLength;
import com.apps.quantitymeasurement.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    // ✅ Yard Tests
    @Test
    public void testEquality_YardToYard_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(1.0, LengthUnit.YARD)));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(2.0, LengthUnit.YARD)));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(3.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(new QuantityLength(3.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.YARD)));
    }

    @Test
    public void testEquality_YardToInch_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(36.0, LengthUnit.INCH)));
    }

    @Test
    public void testEquality_InchToYard_EquivalentValue() {
        assertTrue(new QuantityLength(36.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.YARD)));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalent() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    // ✅ Centimeter Tests
    @Test
    public void testEquality_CmToCm_SameValue() {
        assertTrue(new QuantityLength(2.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(2.0, LengthUnit.CENTIMETER)));
    }

    @Test
    public void testEquality_CmToInch_EquivalentValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(0.393701, LengthUnit.INCH)));
    }

    @Test
    public void testEquality_CmToFeet_NonEquivalent() {
        assertFalse(new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    // ✅ Transitive Property
    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    // ✅ Null + Safety
    @Test
    public void testEquality_NullComparison() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARD).equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        assertTrue(q.equals(q));
    }

    @Test
    public void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }

    // ✅ Complex Scenario
    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        assertTrue(new QuantityLength(2.0, LengthUnit.YARD)
                .equals(new QuantityLength(6.0, LengthUnit.FEET)));

        assertTrue(new QuantityLength(6.0, LengthUnit.FEET)
                .equals(new QuantityLength(72.0, LengthUnit.INCH)));
    }
}
