package com.inditex.logandotest.api.service;

import com.inditex.logandotest.api.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

public interface GetProductSizes {
    Set<Long> apply();
}

//La primera es cuando una talla está marcada como back soon, en este caso, aunque la talla
//no tenga stock, el producto debe mostrarse igual, puesto que es un producto que va a
//volver a estar a la venta cuando vuelva a entrar stock.

//La segunda es cuando un producto tiene tallas “especiales”, en este caso el producto solo
//estará visible si al menos una talla especial y una no especial tiene stock (o está marcada
//como back soon). Si solo tienen stock (o están marcadas como back soon) tallas de uno de
//los dos grupos el producto no debe mostrarse.
@Component
@AllArgsConstructor
class GetProductSizesImpl implements GetProductSizes {

    private final SizeRepository repository;

    @Override
    public Set<Long> apply() {
        final var productSizesMap = new HashMap<Long, AvailableSize>();
        final var sizeList = repository.findAll();

        sizeList.stream()
                .filter(size -> size.isBackSoon() || (size.getStock() != null && size.getStock().getQuantity() > 0))
                .forEach(size -> {
                    var availableSize = productSizesMap.get(size.getProduct().getId());
                    if (availableSize == null) {
                        availableSize = new AvailableSize();
                    }
                    if (size.isBackSoon() && !size.isSpecial()) {
                        availableSize.setBackSoon(true);
                    } else if (size.isSpecial() &&
                            (size.getStock() != null && size.getStock().getQuantity() > 0)) {
                        availableSize.setHasSpecialSize(true);
                    } else if (!size.isSpecial() &&
                            (size.getStock() != null && size.getStock().getQuantity() > 0)) {
                        availableSize.setHasNonSpecialSize(true);
                    }

                    productSizesMap.put(size.getProduct().getId(), availableSize);
                });
        return getResult(productSizesMap);
    }

    private Set<Long> getResult(Map<Long, AvailableSize> productSizesMap) {
        final var result = new HashSet<Long>();
        productSizesMap.entrySet().forEach(productSizeEntry -> {
            final var availableSize = productSizeEntry.getValue();
            if (availableSize.isBackSoon) {
                result.add(productSizeEntry.getKey());
            } else if (availableSize.hasNonSpecialSize && availableSize.hasSpecialSize) {
                result.add(productSizeEntry.getKey());
            }
        });
        return result;
    }

}