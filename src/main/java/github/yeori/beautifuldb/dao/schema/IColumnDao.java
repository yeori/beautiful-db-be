package github.yeori.beautifuldb.dao.schema;

import org.springframework.data.jpa.repository.JpaRepository;

import github.yeori.beautifuldb.model.schema.Column;

public interface IColumnDao extends JpaRepository<Column, Long> {

}
