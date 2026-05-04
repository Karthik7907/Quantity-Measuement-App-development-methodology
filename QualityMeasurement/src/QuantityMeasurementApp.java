package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ✅ Extended Enum with new units
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084); // 1 cm = 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toBase(double value) {
            return value * toFeetFactor;
        }
    }

    // ✅ Generic Quantity Class (UNCHANGED)
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        public double toFeet() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Optional tolerance for floating point safety
            return Math.abs(this.toFeet() - other.toFeet()) < 0.0001;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toFeet());
        }
    }

    // ✅ Demo
    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength q3 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength q4 = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("1 Yard == 3 Feet ? " + q1.equals(q2));
        System.out.println("1 cm == 0.393701 inch ? " + q3.equals(q4));
    }
}