package com.example.springbootbooksellerapp.service;

import com.example.springbootbooksellerapp.model.PurchaseHistory;
import com.example.springbootbooksellerapp.repository.IPurchaseHistoryRepository;
import com.example.springbootbooksellerapp.repository.projection.IPurchaseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseHistoryService implements  IPurchaseHistoryService{

    @Autowired
    private IPurchaseHistoryRepository purchaseHistoryRepository;


    //constructor injection yöntemi üsteki metodla aynı işlemi görüyor

//    private final IPurchaseHistoryRepository purchaseHistoryRepository;
//
//    public PurchaseHistoryService(IPurchaseHistoryRepository purchaseHistoryRepository) {
//        this.purchaseHistoryRepository = purchaseHistoryRepository;
//    }

    @Override
    public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory){

        purchaseHistory.setPurchaseTime(LocalDateTime.now());
        return purchaseHistoryRepository.save(purchaseHistory);

    }

    @Override
    public List<IPurchaseItem> findAllPurchasesOfUser(Long userId){

        return    purchaseHistoryRepository.findAllPurchasesOfUser(userId);
    }



}