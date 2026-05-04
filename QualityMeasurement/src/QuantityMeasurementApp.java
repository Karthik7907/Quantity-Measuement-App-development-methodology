package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static QuantityWeight convert(double value, WeightUnit from, WeightUnit to) {
        return new QuantityWeight(value, from).convertTo(to);
    }

    public static QuantityWeight add(double v1, WeightUnit u1, double v2, WeightUnit u2, WeightUnit target) {
        return QuantityWeight.add(
                new QuantityWeight(v1, u1),
                new QuantityWeight(v2, u2),
                target
        );
    }

    public static boolean equals(double v1, WeightUnit u1, double v2, WeightUnit u2) {
        return new QuantityWeight(v1, u1)
                .equals(new QuantityWeight(v2, u2));
    }

    public static void main(String[] args) {
        System.out.println(add(1.0, WeightUnit.KILOGRAM, 1000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM));
        System.out.println(convert(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM));
        System.out.println(equals(1.0, WeightUnit.KILOGRAM, 1000.0, WeightUnit.GRAM));
    }
}
