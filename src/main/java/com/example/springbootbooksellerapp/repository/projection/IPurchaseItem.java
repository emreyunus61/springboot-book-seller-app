package com.example.springbootbooksellerapp.repository.projection;

import java.time.LocalDateTime;

public interface IPurchaseItem {

    String getTitle();
    Double getPrice();

    LocalDateTime getPurchaseTime();

}