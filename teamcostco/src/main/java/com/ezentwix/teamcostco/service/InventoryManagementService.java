package com.ezentwix.teamcostco.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryManagementService {

    // 예시 필드, 실제 데이터베이스나 다른 저장소를 사용해야 합니다.
    private int receivedQty;
    private int defectiveQty;

    public InventoryManagementService() {
        this.receivedQty = 0;
        this.defectiveQty = 0;
    }

    public void processReceivedQty(int qty) {
        // 실제 입고 수량 처리 로직 구현
        if (qty < 0) {
            throw new IllegalArgumentException("처리수량은 음수가 될 수 없습니다.");
        }
        this.receivedQty += qty;
        // 데이터베이스 업데이트 로직
        System.out.println("Processing received quantity: " + qty);
    }

    public void processDefectiveQty(int qty) {
        // 실제 불량 수량 처리 로직 구현
        if (qty < 0) {
            throw new IllegalArgumentException("처리수량은 음수가 될 수 없습니다.");
        }
        this.defectiveQty += qty;
        // 데이터베이스 업데이트 로직
        System.out.println("Processing defective quantity: " + qty);
    }

    public int getReceivedQty() {
        return receivedQty;
    }

    public int getDefectiveQty() {
        return defectiveQty;
    }
}
