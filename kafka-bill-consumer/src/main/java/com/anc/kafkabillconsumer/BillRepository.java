package com.anc.kafkabillconsumer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
}
