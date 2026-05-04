package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // =========================
    // LENGTH UNIT ENUM
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
                throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        // =========================
        // UC5: CONVERSION
        // =========================
        public static double convert(double value, LengthUnit from, LengthUnit to) {

            if (from == null || to == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double feet = from.toFeet(value);
            return to.fromFeet(feet);
        }

        // =========================
        // UC6 + UC7: ADDITION
        // =========================
        public static QuantityLength add(
                QuantityLength q1,
                QuantityLength q2,
                LengthUnit targetUnit) {

            if (q1 == null || q2 == null || targetUnit == null)
                throw new IllegalArgumentException("Null not allowed");

            double sumFeet =
                    q1.unit.toFeet(q1.value) +
                            q2.unit.toFeet(q2.value);

            double resultValue = targetUnit.fromFeet(sumFeet);

            return new QuantityLength(resultValue, targetUnit);
        }

        // =========================
        // BASE CONVERSION
        // =========================
        private double toFeet() {
            return unit.toFeet(value);
        }

        // =========================
        // EQUALITY (UC6 + UC7 FIX)
        // =========================
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // EPSILON FIX FOR ALL 14 TEST CASES
            return Math.abs(this.toFeet() - other.toFeet()) < 0.0001;
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
}