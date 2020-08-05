package github.yeori.beautifuldb.service.schema;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.yeori.beautifuldb.dao.schema.IVendorColumnTypeDao;
import github.yeori.beautifuldb.model.schema.Vendor;
import github.yeori.beautifuldb.model.schema.VendorColumnType;
import github.yeori.dtomimic.DtoMimic;

@Service
public class VendorService {

	@Autowired
	IVendorColumnTypeDao typeDao;
	
	@Autowired
	DtoMimic mimiker;
	
	public List<VendorColumnType> listColumnTypes(Integer vendorSeq) {
		Vendor v = new Vendor();
		v.setSeq(vendorSeq);
		List<VendorColumnType> types = typeDao.findByVendor(v);
		return mimiker.mimic(types, ArrayList.class, "vendor");
	}

}
