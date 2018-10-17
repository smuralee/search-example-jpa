package com.search.util;

import com.search.model.domain.Item;
import com.search.model.domain.Location;
import com.search.model.domain.Supplier;
import com.search.persistence.entities.Product;
import com.search.persistence.entities.Region;
import com.search.persistence.entities.SupplyChain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class FunctionsUtil {

    /**
     * <p>
     * Convert the {@link Region} to {@link Location} object
     * </p>
     */
    private static Function<Region, Location> getLocation = region -> {
        Location location = new Location();
        location.setId(region.getId());
        location.setName(region.getName());
        return location;
    };

    /**
     * <p>
     * Convert the {@link SupplyChain} to {@link Supplier} object
     * </p>
     */
    private static Function<SupplyChain, Supplier> getSupplier = supplyChain -> {
        Supplier supplier = new Supplier();
        supplier.setId(supplyChain.getId());
        supplier.setName(supplyChain.getName());
        return supplier;
    };

    /**
     * <p>
     * Convert the {@link Product} to {@link Item} object
     * </p>
     */
    public static Function<Product, Item> getItem = product -> {
        Item item = new Item();
        item.setId(product.getId());
        item.setName(product.getName());
        item.setBrand(product.getManufacturer());

        // Convert from region to location
        final List<Location> locations = product.getRegions()
                .stream()
                .map(getLocation)
                .collect(Collectors.toList());

        // Convert from supply chains to suppliers
        final List<Supplier> suppliers = product.getSupplyChains()
                .stream()
                .map(getSupplier)
                .collect(Collectors.toList());

        item.setLocations(locations);
        item.setSuppliers(suppliers);

        return item;
    };

    private FunctionsUtil() {
    }


}
