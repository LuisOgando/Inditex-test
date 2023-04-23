package com.inditex.logandotest.api.service;

import com.inditex.logandotest.api.model.Size;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

public interface GetProductInStock {
    Set<Long> apply(List<Size> sizeList);
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
class GetProductInStockImpl implements GetProductInStock {

    @Override
    public Set<Long> apply(List<Size> sizeList) {
        final var productSizesMap = new HashMap<Long, SizeInStock>();

        sizeList.stream()
                .filter(size -> size.isBackSoon() || (size.getStock() != null && size.getStock().getQuantity() > 0))
                .forEach(size -> {
                    var sizeInStock = productSizesMap.get(size.getProduct().getId());
                    if (sizeInStock == null) {
                        sizeInStock = new SizeInStock();
                    }
                    if (size.isBackSoon() && !size.isSpecial()) {
                        sizeInStock.setBackSoon(true);
                    } else if (size.isSpecial() &&
                            (size.getStock() != null && size.getStock().getQuantity() > 0)) {
                        sizeInStock.setHasSpecialSize(true);
                    } else if (!size.isSpecial() &&
                            (size.getStock() != null && size.getStock().getQuantity() > 0)) {
                        sizeInStock.setHasNonSpecialSize(true);
                    }

                    productSizesMap.put(size.getProduct().getId(), sizeInStock);
                });

        return getResult(productSizesMap);
    }

    private Set<Long> getResult(Map<Long, SizeInStock> productSizesMap) {
        final var result = new HashSet<Long>();
        productSizesMap.forEach((key, sizeInStock) -> {
            if (sizeInStock.isBackSoon) {
                result.add(key);
            } else if (sizeInStock.hasNonSpecialSize && sizeInStock.hasSpecialSize) {
                result.add(key);
            }
        });
        return result;
    }

}