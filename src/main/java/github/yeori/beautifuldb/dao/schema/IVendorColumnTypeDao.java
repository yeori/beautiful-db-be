package github.yeori.beautifuldb.dao.schema;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.schema.VendorColumnType;

@Repository
public interface IVendorColumnTypeDao extends JpaRepository<VendorColumnType, Long>{

	List<VendorColumnType> findByVendor(Vendor vendor);
}
