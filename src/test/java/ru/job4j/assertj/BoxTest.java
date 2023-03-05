package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 3);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(4, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenVertex10() {
        Box box = new Box(10, 2);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(-1);
    }

    @Test
    void whenVertex4() {
        Box box = new Box(4, 4);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isGreaterThan(-1);
    }

    @Test
    void whenNotExist() {
        Box box = new Box(12, 4);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void whenIsExist() {
        Box box = new Box(0, 2);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void whenAreaCube() {
        Box box = new Box(8, 3);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(54.0, offset(0.1));
    }

    @Test
    void whenAreaTetrahedron() {
        Box box = new Box(4, 2);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(6.9, offset(0.1));
    }

    @Test
    void whenNotArea() {
        Box box = new Box(0, 0);
        double rsl = box.getArea();
        assertThat(rsl).isEqualTo(0);
    }
}