package com.inditex.logandotest.api.service;

import lombok.Data;

@Data
class AvailableSize {
    boolean hasSpecialSize;
    boolean hasNonSpecialSize;
    boolean isBackSoon;
}
