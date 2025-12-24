package de.lighty.foxutils.events.entity;

import de.lighty.foxutils.events.entity.handlers.IEntityInteractHandler;

public class EntityEventRegistry {
    public static class Interact {
        public static void register(IEntityInteractHandler handler){
            EntityInteractEventRegistryImpl.register(handler);
        }
    }
}
