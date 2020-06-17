package br.com.andrenunes.calc.model;

@FunctionalInterface
public interface MemoryObserver {
    void alternateValue(String newValue);
}

