package com.sapo.salemanagement.repositories.vendor;

import com.sapo.salemanagement.models.ImportItem;
import com.sapo.salemanagement.models.keys.ImportItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportItemRepository extends JpaRepository<ImportItem, ImportItemKey> {
    List<ImportItem> findById_ImportOrderId(int importOrderId);
}
