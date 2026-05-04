package com.apps.quantitymeasurement;

import java.util.Objects;

public class QuantityMeasurementApp {

    // =========================
    // QuantityLength (UC8 FINAL)
    // =========================
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Getter (fix for your error: value has private access)
        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        // =========================
        // CONVERT
        // =========================
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = unit.convertToBaseUnit(value);
            double converted = targetUnit.convertFromBaseUnit(base);

            return new QuantityLength(converted, targetUnit);
        }

        // =========================
        // EQUALITY (UC1–UC8 COMPATIBLE)
        // =========================
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;

            double thisBase = this.unit.convertToBaseUnit(this.value);
            double otherBase = other.unit.convertToBaseUnit(other.value);

            return Math.abs(thisBase - otherBase) < 0.000001;
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit.convertToBaseUnit(value));
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }

        // =========================
        // UC6 + UC7 ADDITION
        // =========================

        // UC6 → default target = first operand unit
        public static QuantityLength add(QuantityLength l1, QuantityLength l2) {
            return add(l1, l2, l1.unit);
        }

        // UC7 → explicit target unit
        public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {

            if (l1 == null || l2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Null not allowed");
            }

            double base1 = l1.unit.convertToBaseUnit(l1.value);
            double base2 = l2.unit.convertToBaseUnit(l2.value);

            double sumBase = base1 + base2;

            double result = targetUnit.convertFromBaseUnit(sumBase);

            return new QuantityLength(result, targetUnit);
        }
    }

    // =========================
    // MAIN (Demo)
    // =========================
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1.equals(q2));
        System.out.println(q1.convertTo(LengthUnit.INCH));
        System.out.println(QuantityLength.add(q1, q2, LengthUnit.YARD));
    }
}