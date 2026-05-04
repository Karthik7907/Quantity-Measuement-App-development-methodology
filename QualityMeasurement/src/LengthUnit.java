package com.apps.quantitymeasurement;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(1.0 / 30.48);

    private final double factorToFeet;

    LengthUnit(double factorToFeet) {
        this.factorToFeet = factorToFeet;
    }

    // Convert value of THIS unit → FEET (base unit)
    public double convertToBaseUnit(double value) {
        return value * factorToFeet;
    }

    // Convert FEET → THIS unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factorToFeet;
    }
}