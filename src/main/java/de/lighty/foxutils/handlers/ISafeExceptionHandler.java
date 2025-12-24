package de.lighty.foxutils.handlers;

@FunctionalInterface
public interface ISafeExceptionHandler {
    void handle() throws Exception;
}
