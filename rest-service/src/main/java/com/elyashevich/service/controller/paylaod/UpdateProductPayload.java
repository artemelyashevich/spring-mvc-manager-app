package com.elyashevich.service.controller.paylaod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotNull()
        @Size(min = 3, max = 50)
        String title,
        @NotNull()
        @Size(max = 1000)
        String details
) {
}
