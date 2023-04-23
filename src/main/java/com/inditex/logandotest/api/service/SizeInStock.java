package com.inditex.logandotest.api.service;

import lombok.Data;

@Data
class SizeInStock {
    boolean hasSpecialSize;
    boolean hasNonSpecialSize;
    boolean isBackSoon;
}
