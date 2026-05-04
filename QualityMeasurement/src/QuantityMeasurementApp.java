package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // =========================
    // UNIT ENUM
    // =========================
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // =========================
    // VALUE OBJECT
    // =========================
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            this.value = value;
            this.unit = unit;
        }

        // =========================
        // UC5: CONVERSION API
        // =========================
        public static double convert(double value, LengthUnit from, LengthUnit to) {
            if (from == null || to == null)
                throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double inFeet = from.toFeet(value);
            return to.fromFeet(inFeet);
        }

        // =========================
        // UC6: ADDITION
        // =========================
        public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit resultUnit) {

            if (q1 == null || q2 == null || resultUnit == null)
                throw new IllegalArgumentException("Null not allowed");

            double q1Feet = q1.unit.toFeet(q1.value);
            double q2Feet = q2.unit.toFeet(q2.value);

            double sumFeet = q1Feet + q2Feet;

            double resultValue = resultUnit.fromFeet(sumFeet);

            return new QuantityLength(resultValue, resultUnit);
        }

        // =========================
        // INTERNAL CONVERSION (FOR EQUALS)
        // =========================
        private double toFeet() {
            return unit.toFeet(value);
        }

        // =========================
        // EQUALITY
        // =========================
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(this.toFeet() - other.toFeet()) < 1e-6;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // =========================
    // DEMO METHODS (OPTIONAL)
    // =========================
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        System.out.println("Result: " + result);
    }
}