package Comparadores;

import java.util.Comparator;

import Modelo.Activo;

public class ActivoComparatorPorNomenclatura implements Comparator<Activo> {
    @Override
    public int compare(Activo a1, Activo a2) {
        return a1.getNomenclatura().compareTo(a2.getNomenclatura());
    }
}