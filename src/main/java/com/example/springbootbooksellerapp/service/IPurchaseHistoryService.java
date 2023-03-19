package com.example.springbootbooksellerapp.service;

import com.example.springbootbooksellerapp.model.PurchaseHistory;
import com.example.springbootbooksellerapp.repository.projection.IPurchaseItem;

import java.util.List;

public interface IPurchaseHistoryService {
    PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

    List<IPurchaseItem> findAllPurchasesOfUser(Long userId);
}
