package github.yeori.beautifuldb.dao.schema;

import org.springframework.data.jpa.repository.JpaRepository;

import github.yeori.beautifuldb.model.schema.Vendor;

public interface IVendorDao extends JpaRepository<Vendor, Integer> {

}
