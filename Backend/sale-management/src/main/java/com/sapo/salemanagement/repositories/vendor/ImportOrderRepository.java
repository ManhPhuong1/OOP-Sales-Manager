package com.sapo.salemanagement.repositories.vendor;

import com.sapo.salemanagement.models.ImportOrder;
import com.sapo.salemanagement.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportOrderRepository extends JpaRepository<ImportOrder, Integer> {
    @Query("SELECT import FROM ImportOrder import WHERE import.vendor.name = :vendorName")
    List<ImportOrder> findByVendorName(String vendorName);
    List<ImportOrder> findImportOrderByVendor(Vendor vendor);
}
